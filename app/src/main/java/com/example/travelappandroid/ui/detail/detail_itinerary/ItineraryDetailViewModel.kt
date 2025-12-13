package com.example.travelappandroid.ui.detail.detail_itinerary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelappandroid.data.model.Itinerary
import com.example.travelappandroid.data.model.Place
import com.example.travelappandroid.data.repository.ItineraryRepository
import com.example.travelappandroid.data.repository.PlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItineraryDetailViewModel @Inject constructor(private val itineraryRepository: ItineraryRepository, private val placeRepository: PlaceRepository) : ViewModel() {
    private val _itinerary = MutableLiveData<Itinerary?>()
    val itinerary: LiveData<Itinerary?> = _itinerary

    private val _timelinePlaces = MutableLiveData<List<Place>>()
    val timelinePlaces: LiveData<List<Place>> = _timelinePlaces

    fun loadItineraryDetail(id: String) {
        viewModelScope.launch {
            val data = itineraryRepository.getItineraryDetail(id)
            _itinerary.value = data

            if (data != null && !data.places.isNullOrEmpty()) {
                val listPlace = mutableListOf<Place>()
                for (placeId in data.places) {
                    val place = placeRepository.getPlaceDetail(placeId)
                    if (place != null) {
                        listPlace.add(place)
                    }
                }
                _timelinePlaces.value = listPlace
            } else {
                _timelinePlaces.value = emptyList()
            }
        }
    }
}