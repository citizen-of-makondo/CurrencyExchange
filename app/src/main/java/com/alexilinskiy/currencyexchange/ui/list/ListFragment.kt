package com.alexilinskiy.currencyexchange.ui.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexilinskiy.currencyexchange.R
import com.alexilinskiy.currencyexchange.data.mapper.MapModel
import com.alexilinskiy.currencyexchange.databinding.FragmentListBinding
import com.alexilinskiy.currencyexchange.ui.MainViewModel
import com.alexilinskiy.currencyexchange.ui.RatesListAdapter
import com.alexilinskiy.currencyexchange.data.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: MainViewModel

    lateinit var spinner: Spinner
    lateinit var ratesList: RecyclerView
    private var adapter: RatesListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        spinner = binding.chooseCurrencyItem.spinner
        ratesList = binding.ratesList

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeView()
        observeViewModel()
    }

    private fun observeView() {
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                index: Int,
                long: Long
            ) {
                viewModel.setCurrencyChanged(index = index, typeSort = arguments?.getString("typeSort"))
            }

            override fun onNothingSelected(p0: AdapterView<*>?) = Unit
        }
        adapter = RatesListAdapter { rate ->
            viewModel.onFavoriteIconClick(rate)
        }
        ratesList.layoutManager = LinearLayoutManager(requireContext())
        ratesList.adapter = adapter

        binding.chooseCurrencyItem.filterButton.setOnClickListener {view ->
            val action = ListFragmentDirections.actionNavigationListToSortFragment()
            view.findNavController().navigate(action)
        }
    }

    private fun observeViewModel() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currenciesFlow.collect { state ->
                    progressCircular.isVisible = state == State.LoadingState
                    when (state) {
                        is State.DataState -> {
                            val spinnerList = MapModel.mapCurrenciesToSpinner(state.data)
                            val adapter =
                                ArrayAdapter(requireContext(), R.layout.spinner_item, spinnerList)
                            adapter.setDropDownViewResource(R.layout.spinner_item)
                            spinner.adapter = adapter
                        }
                        is State.ErrorState -> showError(getString(R.string.error_message))
                        else -> Unit
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.ratesFlow.collect { state ->
                    binding.progressCircular.isVisible = state == State.LoadingState
                    when (state) {
                        is State.DataState -> {
                            adapter?.submitList(state.data)
                        }
                        is State.ErrorState -> showError(getString(R.string.error_message))
                        else -> Unit
                    }
                }
            }
        }
    }

    private fun showError(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setNeutralButton(
                "Try again"
            ) { _, _ ->
                viewModel.getAllCurrencies()
            }
            .setCancelable(false)
            .create()
            .show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}