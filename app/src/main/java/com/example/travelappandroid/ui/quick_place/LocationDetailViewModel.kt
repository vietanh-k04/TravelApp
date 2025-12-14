package com.example.travelappandroid.ui.quick_place

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelappandroid.data.model.Food
import com.example.travelappandroid.data.model.Itinerary
import com.example.travelappandroid.data.model.Place
import com.example.travelappandroid.data.repository.FoodRepository
import com.example.travelappandroid.data.repository.ItineraryRepository
import com.example.travelappandroid.data.repository.PlaceRepository
import com.example.travelappandroid.utils.toNoAccent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val placeRepo: PlaceRepository,
    private val foodRepo: FoodRepository,
    private val itineraryRepo: ItineraryRepository
) : ViewModel() {

    private val _places = MutableLiveData<List<Place>>()
    val places: LiveData<List<Place>> = _places

    private val _foods = MutableLiveData<List<Food>>()
    val foods: LiveData<List<Food>> = _foods

    private val _itineraries = MutableLiveData<List<Itinerary>>()
    val itineraries: LiveData<List<Itinerary>> = _itineraries

    fun loadData(locationName: String) {
        viewModelScope.launch {
            val normalizer = locationName.toNoAccent()
            _places.value = placeRepo.getPlacesByProvince(normalizer)

            _foods.value = foodRepo.getFoodsByProvince(normalizer)

            _itineraries.value = itineraryRepo.getItineraryByProvince(normalizer)
        }
    }
}