package com.example.travelappandroid.data.remote

import com.example.travelappandroid.data.model.Place
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


class PlaceFirebaseDataSource(firestore: FirebaseFirestore) {
    private val placeRef = firestore.collection("places")

    suspend fun getAllPlaces(): List<Place> {
        return try {
            val snapshot = placeRef.get().await()
            snapshot.toObjects(Place::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getTrendingPlaces(): List<Place> {
        return try {
            val snapshot = placeRef.whereEqualTo("isTrending", true).get().await()
            snapshot.toObjects(Place::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}