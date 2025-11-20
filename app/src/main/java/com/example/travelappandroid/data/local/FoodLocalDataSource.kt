package com.example.travelappandroid.data.local

import com.example.travelappandroid.data.dao.FoodDAO
import com.example.travelappandroid.data.mapper.toEntity
import com.example.travelappandroid.data.mapper.toModel
import com.example.travelappandroid.data.model.Food
import com.google.gson.Gson

class FoodLocalDataSource(private val foodDAO: FoodDAO, private val gson: Gson) {
    suspend fun getAllFoods(): List<Food> {
        return foodDAO.getAllFoods().map { it.toModel(gson) }
    }

    suspend fun getFoodById(id: String): Food? {
        return foodDAO.getById(id)?.toModel(gson)
    }

    suspend fun getFoodsByProvince(province: String): List<Food> {
        return foodDAO.getByProvince(province).map { it.toModel(gson) }
    }

    suspend fun searchFoods(keyword: String): List<Food> {
        return foodDAO.search(keyword).map { it.toModel(gson) }
    }

    suspend fun saveFoods(list: List<Food>) {
        foodDAO.clear()
        foodDAO.insertAll(list.map { it.toEntity(gson) })
    }
}