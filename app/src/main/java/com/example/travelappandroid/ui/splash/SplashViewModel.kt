package com.example.travelappandroid.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelappandroid.data.repository.BannerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val bannerRepository: BannerRepository) : ViewModel() {
    private val _isDataReady = MutableLiveData<Boolean>()
    val isDataReady: LiveData<Boolean> = _isDataReady

    fun loadData() {
        viewModelScope.launch {
            bannerRepository.refreshBanners()
            _isDataReady.postValue(true)
        }
    }
}