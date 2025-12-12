package com.example.travelappandroid.data.local

import com.example.travelappandroid.data.dao.ItineraryDAO
import com.example.travelappandroid.data.mapper.toEntity
import com.example.travelappandroid.data.mapper.toModel
import com.example.travelappandroid.data.model.Itinerary
import com.google.gson.Gson

class ItineraryLocalDataSource(private val itineraryDAO: ItineraryDAO, private val gson: Gson) {
    suspend fun getAllItineraries(): List<Itinerary> {
        return itineraryDAO.getAllItineraries().map { it.toModel(gson) }
    }

    suspend fun getById(id: String): Itinerary? {
        return itineraryDAO.getById(id)?.toModel(gson)
    }

    suspend fun search(keyword: String): List<Itinerary> {
        return itineraryDAO.search(keyword).map { it.toModel(gson) }
    }

    suspend fun getTop(count: Int): List<Itinerary> {
        return itineraryDAO.getTop(count).map { it.toModel(gson) }
    }

    suspend fun saveItineraries(list: List<Itinerary>) {
        itineraryDAO.clear()
        itineraryDAO.insertAll(list.map { it.toEntity(gson) })
    }
}