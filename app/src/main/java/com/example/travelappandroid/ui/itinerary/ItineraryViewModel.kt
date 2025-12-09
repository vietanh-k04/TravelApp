package com.example.travelappandroid.ui.itinerary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelappandroid.data.model.Itinerary
import com.example.travelappandroid.data.repository.ItineraryRepository
import com.example.travelappandroid.utils.toNoAccent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItineraryViewModel @Inject constructor(private val repository: ItineraryRepository) : ViewModel() {
    private val _plans = MutableLiveData<List<Itinerary>>()

    val plans: LiveData<List<Itinerary>> = _plans

    fun loadPlans() {
        viewModelScope.launch {
            _plans.value = repository.getAllItineraries()
        }
    }

    fun searchPlans(keyword: String) {
        viewModelScope.launch {
            val normalizer = keyword.toNoAccent()
            _plans.value = if(keyword.isBlank()) {
                repository.getAllItineraries()
            } else {
                repository.searchItineraries(normalizer)
            }
        }
    }
}