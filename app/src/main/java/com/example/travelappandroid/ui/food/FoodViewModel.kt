package com.example.travelappandroid.ui.food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelappandroid.data.model.Food
import com.example.travelappandroid.data.repository.FoodRepository
import com.example.travelappandroid.utils.toNoAccent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(private val repository: FoodRepository) : ViewModel() {
    private val _foods = MutableLiveData<List<Food>>()
    val foods: LiveData<List<Food>> = _foods

    fun search(keyword: String) {
        viewModelScope.launch {
            val normalizer = keyword.toNoAccent()
            _foods.value = if(keyword.isBlank()) {
                repository.getAllFoods()
            }
            else {
                repository.searchFoods(normalizer)
            }
        }
    }

    fun loadFoods() {
        viewModelScope.launch {
            _foods.value = repository.getAllFoods()
        }
    }
}