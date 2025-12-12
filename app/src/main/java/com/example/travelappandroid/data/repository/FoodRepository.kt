package com.example.travelappandroid.data.repository

import com.example.travelappandroid.data.local.FoodLocalDataSource
import com.example.travelappandroid.data.model.Food
import com.example.travelappandroid.data.remote.FoodFirebaseDataSource

class FoodRepository(private val local: FoodLocalDataSource, private val remote: FoodFirebaseDataSource) {
    suspend fun getAllFoods(): List<Food> = local.getAllFoods()

    suspend fun getFoodsByProvince(province: String): List<Food> = local.getFoodsByProvince(province)

    suspend fun searchFoods(keyword: String): List<Food> = local.searchFoods(keyword)

    suspend fun getTop(count: Int): List<Food> = local.getTop(count)

    suspend fun getFoodDetail(id: String): Food? = local.getFoodById(id)

    suspend fun refreshFoods() {
        val list = remote.getAllFoods()
        if (list.isNotEmpty()) {
            local.saveFoods(list)
        }
    }
}