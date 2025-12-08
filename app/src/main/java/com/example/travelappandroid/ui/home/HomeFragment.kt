package com.example.travelappandroid.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.travelappandroid.data.model.RegionItem
import com.example.travelappandroid.databinding.FragmentHomeBinding
import com.example.travelappandroid.ui.components.BannerSlider
import com.example.travelappandroid.ui.components.RecommendAdapter
import com.example.travelappandroid.ui.components.TrendingAdapter
import com.example.travelappandroid.ui.food.FoodAdapter
import com.example.travelappandroid.ui.itinerary.ItineraryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var bannerAdapter: BannerAdapter

    private lateinit var recommendAdapter: RecommendAdapter

    private lateinit var trendingAdapter: TrendingAdapter

    private lateinit var regionAdapter: RegionAdapter

    private lateinit var foodAdapter: FoodAdapter

    private lateinit var itineraryAdapter: ItineraryAdapter

    private lateinit var bannerSlider: BannerSlider

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()

        setUpBannerSlide()

        observeViewModel()

        observeRecommends()

        observeTrending()

        observeFood()

        observeItinerary()

        registerOnPage()

        setUpRegion()
    }

    override fun onPause() {
        super.onPause()
        bannerSlider.stop()
    }

    override fun onResume() {
        super.onResume()
        bannerSlider.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        bannerSlider.stop()
    }

    private fun setUpAdapter() {
        bannerAdapter = BannerAdapter()
        binding.homeBanner.bannerViewPager.adapter = bannerAdapter
        binding.homeBanner.bannerIndicator.attachTo(binding.homeBanner.bannerViewPager)

        recommendAdapter = RecommendAdapter()
        binding.homeRecommend.rvRecommend.adapter = recommendAdapter

        trendingAdapter = TrendingAdapter()
        binding.homeTrendingPlaces.rvTrending.adapter = trendingAdapter

        foodAdapter = FoodAdapter()
        binding.homeFood.rvFood.adapter = foodAdapter

        itineraryAdapter = ItineraryAdapter()
        binding.homeItinerary.rvItinerary.adapter = itineraryAdapter
    }

    private fun setUpRegion() {
        regionAdapter = RegionAdapter(dataRegionList())
        binding.homeRegion.rvRegion.adapter = regionAdapter
    }

    private fun setUpBannerSlide() {
        bannerSlider = BannerSlider(binding.homeBanner.bannerViewPager)
    }

    private fun observeViewModel() {
        viewModel.banners.observe(viewLifecycleOwner) { list ->
            bannerAdapter.submitList(list)
            bannerSlider.start()
        }
    }

    private fun observeRecommends() {
        viewModel.recommended.observe(viewLifecycleOwner) {
            recommendAdapter.updateData(it)
        }
    }

    private fun observeTrending() {
        viewModel.trending.observe(viewLifecycleOwner) {
            trendingAdapter.updateData(it)
        }
    }

    private fun observeFood() {
        viewModel.foods.observe(viewLifecycleOwner) {
            foodAdapter.submitList(it)
        }
    }

    private fun observeItinerary() {
        viewModel.itineraries.observe(viewLifecycleOwner) {
            itineraryAdapter.submitList(it)
        }
    }

    private fun registerOnPage() {
        binding.homeBanner.bannerViewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    bannerSlider.restart()
                }
            }
        )
    }

    private fun dataRegionList() : List<RegionItem> {
        return listOf(
            RegionItem(
                title = "Miền Bắc",
                imageUrl = "https://res.cloudinary.com/djngmdetx/image/upload/v1765196150/bac_evevwg.webp",
                regionCode = "North"
            ),
            RegionItem(
                title = "Miền Trung",
                imageUrl = "https://res.cloudinary.com/djngmdetx/image/upload/v1765196150/trung_iletz9.webp",
                regionCode = "Central"
            ),
            RegionItem(
                title = "Miền Nam",
                imageUrl = "https://res.cloudinary.com/djngmdetx/image/upload/v1765196150/nam_yvnxni.webp",
                regionCode = "South"
            )
        )
    }
}