package com.example.travelappandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelappandroid.data.model.Banner
import com.example.travelappandroid.data.model.Place
import com.example.travelappandroid.data.repository.BannerRepository
import com.example.travelappandroid.data.repository.PlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bannerRepository: BannerRepository,
    private val placeRepository: PlaceRepository,
    ) : ViewModel() {
    private val _banners = MutableLiveData<List<Banner>>()
    val banners: LiveData<List<Banner>> = _banners

    private val _recommended = MutableLiveData<List<Place>>()

    val recommended: LiveData<List<Place>> = _recommended

    private val _trending = MutableLiveData<List<Place>>()
    val trending: LiveData<List<Place>> = _trending

    init {
        loadBanners()
        loadRecommended()
        loadTrending()
    }

    fun loadBanners() {
        viewModelScope.launch {
            val data = bannerRepository.getAllBanners()
            _banners.value = data
        }
    }

    fun loadRecommended() {
        viewModelScope.launch {
            val data = placeRepository.getRecommended(4.7)
            _recommended.value = data
        }
    }

    fun loadTrending() {
        viewModelScope.launch {
            val data = placeRepository.getTrendingPlaces()
            _trending.value = data
        }
    }
}