package com.example.travelappandroid.data.remote

import com.example.travelappandroid.data.model.Itinerary
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ItineraryFirebaseDataSource(firestore: FirebaseFirestore) {
    private val itineraryRef = firestore.collection("itineraries")

    suspend fun getAllItineraries(): List<Itinerary> {
        return try {
            val snapshot = itineraryRef.get().await()
            snapshot.toObjects(Itinerary::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}