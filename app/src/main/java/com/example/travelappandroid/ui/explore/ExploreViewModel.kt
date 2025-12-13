package com.example.travelappandroid.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelappandroid.data.model.Place
import com.example.travelappandroid.data.repository.PlaceRepository
import com.example.travelappandroid.utils.toNoAccent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(private val placeRepository: PlaceRepository) : ViewModel() {
    private val _places = MutableLiveData<List<Place>>()

    val places: LiveData<List<Place>> = _places

    private var currentRegion: String? = null

    init {
        loadPlaces()
    }

    fun loadPlaces() {
        viewModelScope.launch {
            val data = placeRepository.getAllPlaces()
            _places.value = data
        }
    }

    fun searchPlaces(keyword: String) {
        viewModelScope.launch {
            if (keyword.isBlank()) {
                if (currentRegion != null) {
                    _places.value = placeRepository.getPlacesByRegion(currentRegion!!)
                } else {
                    loadPlaces()
                }
            } else {
                val normalizer = keyword.toNoAccent()
                placeRepository.searchPlaces(normalizer)
                    .let { list ->
                        _places.value = list
                    }
            }
        }
    }

    fun filterByRegion(regionKey: String) {
        viewModelScope.launch {
            if (currentRegion == regionKey) {
                currentRegion = null
                loadPlaces()
            } else {
                currentRegion = regionKey
                val dbRegionValue = mapRegionKeyToDbValue(regionKey)
                val normalizer = dbRegionValue.toNoAccent()
                _places.value = placeRepository.getPlacesByRegion(normalizer)
            }
        }
    }

    private fun mapRegionKeyToDbValue(key: String): String {
        return when (key) {
            "North" -> "Miền Bắc"
            "Central" -> "Miền Trung"
            "South" -> "Miền Nam"
            else -> ""
        }
    }
}