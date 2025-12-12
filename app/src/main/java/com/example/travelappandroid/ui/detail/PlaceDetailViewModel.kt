package com.example.travelappandroid.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelappandroid.data.model.Place
import com.example.travelappandroid.data.repository.PlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceDetailViewModel @Inject constructor(private val repository: PlaceRepository) : ViewModel() {

    private val _place = MutableLiveData<Place?>()
    val place: LiveData<Place?> = _place

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadPlaceDetail(placeId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val data = repository.getPlaceDetail(placeId)
            _place.value = data
            _isLoading.value = false
        }
    }
}