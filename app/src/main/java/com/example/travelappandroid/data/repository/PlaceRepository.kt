package com.example.travelappandroid.data.repository

import android.media.Rating
import com.example.travelappandroid.data.local.PlaceLocalDataSource
import com.example.travelappandroid.data.model.Place
import com.example.travelappandroid.data.remote.PlaceFirebaseDataSource

class PlaceRepository(private val local: PlaceLocalDataSource, private val remote: PlaceFirebaseDataSource) {
    // Lấy dữ liệu hiển thị từ local
    suspend fun getAllPlaces(): List<Place> {
        return local.getAllPlaces()
    }

    suspend fun getTrendingPlaces(): List<Place> {
        return local.getTrendingPlaces()
    }

    suspend fun getPlacesByRegion(region: String): List<Place> {
        return local.getPlacesByRegion(region)
    }

    suspend fun getPlacesByProvince(province: String): List<Place> {
        return local.getPlacesByProvince(province)
    }

    suspend fun getPlacesByCategory(category: String): List<Place> {
        return local.getPlacesByCategory(category)
    }

    suspend fun searchPlaces(keyword: String): List<Place> {
        return local.searchPlaces(keyword)
    }

    suspend fun getPlaceDetail(id: String): Place? {
        return local.getPlaceById(id)
    }

    suspend fun getRecommended(rating: Double) : List<Place> {
        return local.getRecommended(rating)
    }

    suspend fun refreshAllPlaces() {
        val list = remote.getAllPlaces()
        if (list.isNotEmpty()) {
            local.savePlaces(list)
        }
    }
}