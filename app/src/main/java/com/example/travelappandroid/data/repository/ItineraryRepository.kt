package com.example.travelappandroid.data.repository

import com.example.travelappandroid.data.local.ItineraryLocalDataSource
import com.example.travelappandroid.data.model.Itinerary
import com.example.travelappandroid.data.remote.ItineraryFirebaseDataSource

class ItineraryRepository(private val local: ItineraryLocalDataSource, private val remote: ItineraryFirebaseDataSource) {
    suspend fun getAllItineraries(): List<Itinerary> {
        return local.getAllItineraries()
    }

    suspend fun searchItineraries(keyword: String): List<Itinerary> {
        return local.search(keyword)
    }

    suspend fun getItineraryDetail(id: String): Itinerary? {
        return local.getById(id)
    }

    suspend fun refreshItineraries() {
        val list = remote.getAllItineraries()
        if (list.isNotEmpty()) {
            local.saveItineraries(list)
        }
    }
}