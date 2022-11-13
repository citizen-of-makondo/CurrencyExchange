package com.alexilinskiy.currencyexchange.ui.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.alexilinskiy.currencyexchange.data.Constants
import com.alexilinskiy.currencyexchange.databinding.FragmentSortBinding

class SortFragment : Fragment() {

    private var _binding: FragmentSortBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSortBinding.inflate(inflater, container, false)
        activity?.actionBar?.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerView()
    }

    private fun observerView() = with(binding) {
        sortValuesByAsc.setOnClickListener {
            navigateWithParam(Constants.SORT_VALUES_ASC_PARAM)
        }

        sortValuesByDesc.setOnClickListener {
            navigateWithParam(Constants.SORT_VALUES_DESC_PARAM)
        }

        sortAlphabeticallyByAsc.setOnClickListener {
            navigateWithParam(Constants.SORT_ALPHABETICALLY_ASC_PARAM)
        }

        sortAlphabeticallyByDesc.setOnClickListener {
            navigateWithParam(Constants.SORT_ALPHABETICALLY_DESC_PARAM)
        }
    }

    private fun navigateWithParam(param: String) {
        val action =
            SortFragmentDirections.actionSortFragmentToNavigationList(typeSort = param)
        view?.findNavController()?.navigate(action)
    }

}