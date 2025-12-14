package com.example.travelappandroid.ui.detail.detail_place

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.travelappandroid.R
import com.example.travelappandroid.data.model.Place
import com.example.travelappandroid.databinding.FragmentPlaceDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import androidx.preference.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.core.net.toUri

@Suppress("DEPRECATION")
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
            setupMap(place)
        }
    }

    private fun setUpListeners() {
        binding.detailThumbnail.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupMap(place: Place) {
        // 1. Hiện khung map
        binding.detailPlaceMap.root.visibility = View.VISIBLE
        val mapView = binding.detailPlaceMap.mapView

        Configuration.getInstance().load(
            requireContext(),
            PreferenceManager.getDefaultSharedPreferences(requireContext())
        )
        Configuration.getInstance().userAgentValue = requireContext().packageName

        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)
        val mapController = mapView.controller
        mapController.setZoom(15.0)

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            var targetPoint: GeoPoint? = null
            val title = place.name

            if (place.latitude != null && place.longitude != null && place.latitude != 0.0) {
                targetPoint = GeoPoint(place.latitude, place.longitude)
            }
            else {
                try {
                    val geocoder = Geocoder(requireContext())
                    val addressQuery = place.name ?: ""
                    val results = geocoder.getFromLocationName(addressQuery, 1)
                    if (!results.isNullOrEmpty()) {
                        val location = results[0]
                        targetPoint = GeoPoint(location.latitude, location.longitude)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            withContext(Dispatchers.Main) {
                if (targetPoint != null) {
                    mapController.setCenter(targetPoint)

                    val marker = Marker(mapView)
                    marker.position = targetPoint
                    marker.title = title
                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)

                    mapView.overlays.clear()
                    mapView.overlays.add(marker)
                    mapView.invalidate()

                    binding.detailPlaceMap.btnDirections.visibility = View.VISIBLE
                    binding.detailPlaceMap.btnDirections.setOnClickListener {
                        openGoogleMapsNavigation(targetPoint.latitude, targetPoint.longitude)
                    }
                } else {
                    mapController.setZoom(6.0)
                    mapController.setCenter(GeoPoint(14.0583, 108.2772))
                    binding.detailPlaceMap.btnDirections.visibility = View.GONE
                }
            }
        }
    }

    private fun openGoogleMapsNavigation(lat: Double, lng: Double) {
        val gmmIntentUri = "google.navigation:q=$lat,$lng".toUri()
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        try {
            startActivity(mapIntent)
        } catch (_: Exception) {
            val browserUri = "https://www.google.com/maps/dir/?api=1&destination=$lat,$lng".toUri()
            val browserIntent = Intent(Intent.ACTION_VIEW, browserUri)
            startActivity(browserIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.detailPlaceMap.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.detailPlaceMap.mapView.onPause()
    }
}