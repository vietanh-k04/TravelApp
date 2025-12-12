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
            val normalizer = keyword.toNoAccent()
            _places.value = if(keyword.isBlank()) {
                placeRepository.getAllPlaces()
            }
            else {
                placeRepository.searchPlaces(normalizer)
            }
        }
    }
}