package com.alexilinskiy.currencyexchange.ui.sort

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedDispatcher
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.alexilinskiy.currencyexchange.R
import com.alexilinskiy.currencyexchange.databinding.FragmentListBinding
import com.alexilinskiy.currencyexchange.databinding.FragmentSortBinding
import com.alexilinskiy.currencyexchange.ui.list.ListFragmentDirections

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
        sort.setOnClickListener {
            val action =
                SortFragmentDirections.actionSortFragmentToNavigationList(typeSort = "sort")
            view?.findNavController()?.navigate(action)
        }

        sortByDesc.setOnClickListener {
            val action = SortFragmentDirections.actionSortFragmentToNavigationList(typeSort = "sortByDesc")
            view?.findNavController()?.navigate(action)
        }
    }

}