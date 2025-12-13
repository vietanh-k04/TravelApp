package com.example.travelappandroid.ui.detail.detail_itinerary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.travelappandroid.NavGraphDirections
import com.example.travelappandroid.data.model.Itinerary
import com.example.travelappandroid.databinding.FragmentItineraryDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItineraryDetailFragment : Fragment() {
    private lateinit var binding: FragmentItineraryDetailBinding
    private val viewModel: ItineraryDetailViewModel by viewModels()
    private val args: ItineraryDetailFragmentArgs by navArgs()

    private lateinit var timelineAdapter: TimelineAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentItineraryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.itineraryId
        if (id.isNotEmpty()) {
            viewModel.loadItineraryDetail(id)
        } else {
            findNavController().popBackStack()
        }

        setUpAdapter()
        observeData()

        binding.detailItineraryThumbnail.toolbarItinerary.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setUpAdapter() {
        timelineAdapter = TimelineAdapter { place ->
            val action = NavGraphDirections.actionGlobalPlaceDetailFragment(place.id)
            findNavController().navigate(action)
        }
        binding.detailItineraryPlaces.rvTimeline.adapter = timelineAdapter
    }

    private fun observeData() {
        viewModel.itinerary.observe(viewLifecycleOwner) { item ->
            if (item != null) bindInfo(item)
        }

        viewModel.timelinePlaces.observe(viewLifecycleOwner) { list ->
            timelineAdapter.submitList(list)
        }
    }

    private fun bindInfo(item: Itinerary) {
        binding.apply {
            detailItineraryTitle.tvItineraryName.text = item.title
            detailItineraryTitle.tvDuration.text = item.duration
            detailItineraryDescription.tvItineraryDesc.text = item.description ?: "Chưa có mô tả."

            Glide.with(this@ItineraryDetailFragment)
                .load(item.thumbnail)
                .centerCrop()
                .into(detailItineraryThumbnail.imgItineraryCover)
        }
    }
}