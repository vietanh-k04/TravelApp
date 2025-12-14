package com.example.travelappandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelappandroid.data.model.Banner
import com.example.travelappandroid.data.model.Food
import com.example.travelappandroid.data.model.Itinerary
import com.example.travelappandroid.data.model.Place
import com.example.travelappandroid.data.repository.BannerRepository
import com.example.travelappandroid.data.repository.FoodRepository
import com.example.travelappandroid.data.repository.ItineraryRepository
import com.example.travelappandroid.data.repository.PlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val bannerRepository: BannerRepository, private val placeRepository: PlaceRepository, private val foodRepository: FoodRepository, private val itineraryRepository: ItineraryRepository) : ViewModel() {
    private val _banners = MutableLiveData<List<Banner>>()
    val banners: LiveData<List<Banner>> = _banners

    private val _recommended = MutableLiveData<List<Place>>()

    val recommended: LiveData<List<Place>> = _recommended

    private val _trending = MutableLiveData<List<Place>>()
    val trending: LiveData<List<Place>> = _trending

    private val _foods = MutableLiveData<List<Food>>()
    val foods: LiveData<List<Food>> = _foods

    private val _itineraries = MutableLiveData<List<Itinerary>>()
    val itineraries: LiveData<List<Itinerary>> = _itineraries

    private val _places = MutableLiveData<List<Place>>()
    val places: LiveData<List<Place>> = _trending

    init {
        loadBanners()
        loadRecommended()
        loadTrending()
        loadFoods()
        loadItineraries()
        loadPlaces()
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

    fun loadFoods() {
        viewModelScope.launch {
            _foods.value = foodRepository.getTop(5)
        }
    }

    fun loadItineraries() {
        viewModelScope.launch {
            _itineraries.value = itineraryRepository.getTop(3)
        }
    }

    fun loadPlaces() {
        viewModelScope.launch {
            val data = placeRepository.getAllPlaces()
            _places.value = data
        }
    }
}