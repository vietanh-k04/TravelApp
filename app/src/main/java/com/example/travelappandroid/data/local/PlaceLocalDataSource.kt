package com.example.travelappandroid.data.local

import com.example.travelappandroid.data.dao.PlaceDAO
import com.example.travelappandroid.data.mapper.toEntity
import com.example.travelappandroid.data.mapper.toModel
import com.example.travelappandroid.data.model.Place
import com.google.gson.Gson

class PlaceLocalDataSource(private val placeDAO: PlaceDAO, private val gson: Gson) {
    suspend fun getAllPlaces(): List<Place> {
        return placeDAO.getAllPlaces().map { it.toModel(gson) }
    }

    suspend fun getPlaceById(id: String): Place? {
        return placeDAO.getById(id).toModel(gson)
    }

    suspend fun getTrendingPlaces(): List<Place> {
        return placeDAO.getTrending().map { it.toModel(gson) }
    }

    suspend fun getPlacesByRegion(region: String): List<Place> {
        return placeDAO.getByRegion(region).map { it.toModel(gson) }
    }

    suspend fun getPlacesByProvince(province: String): List<Place> {
        return placeDAO.getByProvince(province).map { it.toModel(gson) }
    }

    suspend fun getPlacesByCategory(category: String): List<Place> {
        return placeDAO.getByCategory(category).map { it.toModel(gson) }
    }

    suspend fun searchPlaces(keyword: String): List<Place> {
        return placeDAO.search(keyword).map { it.toModel(gson) }
    }

    suspend fun getRecommended(rating: Double) : List<Place> {
        return placeDAO.getRecommended(rating).map { it.toModel(gson) }
    }

    suspend fun savePlaces(list: List<Place>) {
        placeDAO.clear()
        placeDAO.insertAll(list.map { it.toEntity(gson) })
    }
}