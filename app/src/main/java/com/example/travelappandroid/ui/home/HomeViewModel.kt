package com.example.travelappandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelappandroid.data.model.Banner
import com.example.travelappandroid.data.repository.BannerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val bannerRepository: BannerRepository) : ViewModel() {
    private val _banners = MutableLiveData<List<Banner>>()
    val banners: LiveData<List<Banner>> = _banners

    init {
        loadBanners()
    }

    fun loadBanners() {
        viewModelScope.launch {
            val data = bannerRepository.getAllBanners()
            _banners.value = data
        }
    }
}