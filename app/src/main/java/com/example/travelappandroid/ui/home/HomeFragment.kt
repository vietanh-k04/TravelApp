package com.example.travelappandroid.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.travelappandroid.databinding.FragmentHomeBinding
import com.example.travelappandroid.ui.components.AutoScrollViewPager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var bannerAdapter: BannerAdapter

    private lateinit var autoScroller: AutoScrollViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()

        autoScroller = AutoScrollViewPager(binding.homeBanner.bannerViewPager)

        observeViewModel()
    }

    fun setUpAdapter() {
        bannerAdapter = BannerAdapter()
        binding.homeBanner.bannerViewPager.adapter = bannerAdapter
        binding.homeBanner.bannerIndicator.attachTo(binding.homeBanner.bannerViewPager)
    }

    private fun observeViewModel() {
        viewModel.banners.observe(viewLifecycleOwner) { list ->
            bannerAdapter.submitList(list)
            autoScroller.start(list.size)
        }
    }

    override fun onPause() {
        super.onPause()
        autoScroller.stop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        autoScroller.release()
    }
}