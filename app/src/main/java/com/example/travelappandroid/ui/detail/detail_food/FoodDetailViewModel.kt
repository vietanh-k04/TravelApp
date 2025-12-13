package com.example.travelappandroid.ui.detail.detail_food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelappandroid.data.model.Food
import com.example.travelappandroid.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(private val foodRepository: FoodRepository, ) : ViewModel() {
    private val _food = MutableLiveData<Food?>()
    val food: LiveData<Food?> = _food

    private val _placeNames = MutableLiveData<List<String>>()
    val placeNames: LiveData<List<String>> = _placeNames

    fun loadFoodDetail(foodId: String) {
        viewModelScope.launch {
            val foodData = foodRepository.getFoodDetail(foodId)
            _food.value = foodData
            _placeNames.value = foodData?.recommendedPlaces ?: emptyList()
        }
    }

}