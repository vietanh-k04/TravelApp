package com.example.travelappandroid.ui.detail.detail_place

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.travelappandroid.R
import com.example.travelappandroid.data.model.Place
import com.example.travelappandroid.databinding.FragmentPlaceDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaceDetailFragment : Fragment() {

    private lateinit var binding: FragmentPlaceDetailBinding
    private val viewModel: PlaceDetailViewModel by viewModels()
    private val args: PlaceDetailFragmentArgs by navArgs()

    private lateinit var tipsAdapter: TipsAdapter

    private lateinit var galleryAdapter: GalleryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlaceDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val placeId = args.placeId
        if (placeId.isNotEmpty()) {
            viewModel.loadPlaceDetail(placeId)
        } else {
            Toast.makeText(context, "Lỗi: Không tìm thấy ID địa điểm", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
        observeData()
        setUpListeners()
    }

    private fun observeData() {
        viewModel.place.observe(viewLifecycleOwner) { place ->
            if (place != null) {
                bindData(place)
            }
        }
    }

    private fun setUpAdapter(place: Place) {
        if (place.tips.isNullOrEmpty()) {
            binding.detailTips.tvTipsHeader.visibility = View.GONE
            binding.detailTips.rvDetailTips.visibility = View.GONE
        } else {
            binding.detailTips.tvTipsHeader.visibility = View.VISIBLE
            binding.detailTips.rvDetailTips.visibility = View.VISIBLE

            tipsAdapter = TipsAdapter(place.tips)
            binding.detailTips.rvDetailTips.adapter = tipsAdapter
        }

        if (place.gallery.isNullOrEmpty()) {
            binding.detailListPicture.rvDetailGallery.visibility = View.GONE
        } else {
            binding.detailListPicture.rvDetailGallery.visibility = View.VISIBLE
            galleryAdapter = GalleryAdapter(place.gallery)
            binding.detailListPicture.rvDetailGallery.adapter = galleryAdapter
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindData(place: Place) {
        binding.apply {
            Glide.with(this@PlaceDetailFragment)
                .load(place.thumbnail)
                .centerCrop()
                .placeholder(R.drawable.bg_home_image)
                .into(detailThumbnail.imgDetailCover)

            detailTitle.tvDetailName.text = place.name
            detailTitle.tvDetailRating.text = place.rating?.toString() ?: "N/A"
            detailRegion.tvDetailLocation.text = "${place.province} • ${place.region}"

            if (place.description.isNullOrEmpty()) {
                detailDescription.tvDetailDesc.text = "Đang cập nhật mô tả..."
            } else {
                detailDescription.tvDetailDesc.text = place.description
            }

            detailIntroduce.tvDetailTime.text = place.openingTime ?: "Tự do"
            detailIntroduce.tvDetailPrice.text = place.ticketPrice ?: "Miễn phí"

            setUpAdapter(place)
        }
    }

    private fun setUpListeners() {
        binding.detailThumbnail.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}