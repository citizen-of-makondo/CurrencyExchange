package com.alexilinskiy.currencyexchange.ui.favorites

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexilinskiy.currencyexchange.R
import com.alexilinskiy.currencyexchange.data.mapper.MapModel
import com.alexilinskiy.currencyexchange.databinding.FragmentFavoritesBinding
import com.alexilinskiy.currencyexchange.ui.RatesListAdapter
import com.alexilinskiy.currencyexchange.data.State
import com.alexilinskiy.currencyexchange.ui.MainViewModel
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
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
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        spinner = binding.chooseCurrencyItem.spinner
        ratesList = binding.ratesList

        observeViewModel()
        observeView()

        viewModel.getFilteredCurrencies()
        return binding.root
    }

    private fun observeView() {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                index: Int,
                long: Long
            ) {
                viewModel.getCountedForCurrency(index, typeSort = null)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) = Unit
        }
        spinner
        adapter = RatesListAdapter { rate ->
            viewModel.onFavoriteIconClick(rate)
        }
        ratesList.layoutManager = LinearLayoutManager(requireContext())
        ratesList.adapter = adapter
        binding.chooseCurrencyItem.filterButton.isVisible = false
    }

    private fun observeViewModel() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currenciesFlow.collect { state ->
                    when (state) {
                        is State.DataState -> {
                            val spinnerList = MapModel.mapCurrenciesToSpinner(state.data)
                            val adapter =
                                ArrayAdapter(requireContext(), R.layout.spinner_item, spinnerList)
                            adapter.setDropDownViewResource(R.layout.spinner_item)
                            spinner.adapter = adapter
                            viewModel.getFavoriteRateList()
                        }
                        is State.ErrorState -> showError(getString(R.string.error_message))
                        else -> Unit
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favoritesRatesFlow.collect { state ->
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

