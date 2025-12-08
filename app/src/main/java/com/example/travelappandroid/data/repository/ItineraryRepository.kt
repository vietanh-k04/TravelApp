package com.example.travelappandroid.data.repository

import com.example.travelappandroid.data.local.ItineraryLocalDataSource
import com.example.travelappandroid.data.local.PlaceLocalDataSource
import com.example.travelappandroid.data.model.Itinerary
import com.example.travelappandroid.data.remote.ItineraryFirebaseDataSource

class ItineraryRepository(private val local: ItineraryLocalDataSource, private val remote: ItineraryFirebaseDataSource, private val placeLocal: PlaceLocalDataSource) {
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
        val itineraries = remote.getAllItineraries()

        if (itineraries.isNotEmpty()) {
            val enriched = itineraries.map { itinerary ->
                val firstPlaceId = itinerary.places?.firstOrNull()

                val thumbnail = if (firstPlaceId != null) {
                    placeLocal.getPlaceById(firstPlaceId)?.thumbnail
                } else ""

                itinerary.apply { this.thumbnail = thumbnail}
            }

            local.saveItineraries(enriched)
        }
    }

}