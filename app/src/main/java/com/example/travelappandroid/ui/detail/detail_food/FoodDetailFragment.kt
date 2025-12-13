package com.example.travelappandroid.ui.detail.detail_food

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.travelappandroid.data.model.Food
import com.example.travelappandroid.databinding.FragmentFoodDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailBinding
    private val viewModel: FoodDetailViewModel by viewModels()
    private val args: FoodDetailFragmentArgs by navArgs()

    private lateinit var placesAdapter: SimplePlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoodDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val foodId = args.foodId
        if (foodId.isNotEmpty()) {
            viewModel.loadFoodDetail(foodId)
        } else {
            findNavController().popBackStack()
        }

        setUpAdapter()
        observeData()

        binding.detailFoodThumbnail.toolbarFood.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpAdapter() {
        placesAdapter = SimplePlaceAdapter()
        binding.detailFoodLocations.rvRecommendedPlaces.adapter = placesAdapter
    }

    private fun observeData() {
        viewModel.food.observe(viewLifecycleOwner) { food ->
            if (food != null)
                bindFoodData(food)
        }

        viewModel.placeNames.observe(viewLifecycleOwner) { names ->
            if (names.isNullOrEmpty()) {
                binding.detailFoodLocations.tvPlacesHeader.visibility = View.GONE
                binding.detailFoodLocations.rvRecommendedPlaces.visibility = View.GONE
            } else {
                binding.detailFoodLocations.tvPlacesHeader.visibility = View.VISIBLE
                binding.detailFoodLocations.rvRecommendedPlaces.visibility = View.VISIBLE
                placesAdapter.submitList(names)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindFoodData(food: Food) {
        binding.apply {
            Glide.with(this@FoodDetailFragment).load(food.thumbnail).centerCrop().into(detailFoodThumbnail.imgFoodCover)
            detailFoodTitle.tvFoodName.text = food.name
            detailFoodTitle.tvFoodProvince.text = "Đặc sản: ${food.province}"
            detailFoodDescription.tvFoodDesc.text = food.description ?: "Chưa có mô tả."
        }
    }
}