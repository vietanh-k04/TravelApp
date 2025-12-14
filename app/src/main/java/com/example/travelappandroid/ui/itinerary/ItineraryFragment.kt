package com.example.travelappandroid.ui.itinerary

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.travelappandroid.NavGraphDirections
import com.example.travelappandroid.databinding.FragmentItineraryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItineraryFragment : Fragment() {
    private lateinit var binding: FragmentItineraryBinding

    private val viewModel: ItineraryViewModel by viewModels()

    private lateinit var adapter: ItineraryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItineraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpObserve()
        searchPlans()
    }

    fun setUpAdapter() {
        adapter = ItineraryAdapter(ItineraryDisplayMode.PLAN) { itinerary ->
            val action = NavGraphDirections.actionGlobalItineraryDetailFragment(itinerary?.id ?: "")
            findNavController().navigate(action)
        }
        binding.itineraryList.rvItinerary.adapter = adapter
    }

    fun setUpObserve() {
        viewModel.plans.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        viewModel.loadPlans()
    }

    fun searchPlans() {
        binding.itineraryHeader.edtSearchItinerary.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchPlans(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}