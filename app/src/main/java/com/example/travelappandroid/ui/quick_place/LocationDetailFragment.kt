package com.example.travelappandroid.ui.quick_place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.travelappandroid.NavGraphDirections
import com.example.travelappandroid.databinding.FragmentLocationDetailBinding
import com.example.travelappandroid.ui.components.RecommendAdapter
import com.example.travelappandroid.ui.food.FoodAdapter
import com.example.travelappandroid.ui.itinerary.ItineraryAdapter
import com.example.travelappandroid.ui.itinerary.ItineraryDisplayMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationDetailFragment : Fragment() {

    private lateinit var binding: FragmentLocationDetailBinding
    private val viewModel: LocationDetailViewModel by viewModels()
    private val args: LocationDetailFragmentArgs by navArgs()

    private lateinit var placeAdapter: RecommendAdapter
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var itineraryAdapter: ItineraryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLocationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locationName = args.locationName
        binding.localTitle.toolbarLocation.title = "Khám phá $locationName"

        viewModel.loadData(locationName)

        setUpAdapters()
        observeData()

        binding.localTitle.toolbarLocation.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpAdapters() {
        placeAdapter = RecommendAdapter { place ->
            val action = NavGraphDirections.actionGlobalPlaceDetailFragment(place?.id ?: "")
            findNavController().navigate(action)
        }
        binding.localPlaces.rvLocationPlaces.adapter = placeAdapter

        foodAdapter = FoodAdapter { food ->
            val action = NavGraphDirections.actionGlobalFoodDetailFragment(food?.id ?: "")
            findNavController().navigate(action)
        }
        binding.localFoods.rvLocationFoods.adapter = foodAdapter

        itineraryAdapter = ItineraryAdapter(ItineraryDisplayMode.HOME) { itinerary ->
            val action = NavGraphDirections.actionGlobalItineraryDetailFragment(itinerary?.id ?: "")
            findNavController().navigate(action)
        }
        binding.localItinerary.rvLocationItineraries.adapter = itineraryAdapter
    }

    private fun observeData() {
        viewModel.places.observe(viewLifecycleOwner) {
            placeAdapter.updateData(it)
            binding.localPlaces.rvLocationPlaces.visibility = if (it.isEmpty()) View.GONE else View.VISIBLE
        }

        viewModel.foods.observe(viewLifecycleOwner) {
            foodAdapter.submitList(it)
            binding.localFoods.rvLocationFoods.visibility = if (it.isEmpty()) View.GONE else View.VISIBLE
        }

        viewModel.itineraries.observe(viewLifecycleOwner) {
            itineraryAdapter.submitList(it)
            binding.localItinerary.rvLocationItineraries.visibility = if (it.isEmpty()) View.GONE else View.VISIBLE
        }
    }
}