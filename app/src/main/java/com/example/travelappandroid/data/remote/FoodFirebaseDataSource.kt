package com.example.travelappandroid.data.remote

import com.example.travelappandroid.data.model.Food
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FoodFirebaseDataSource(firestore: FirebaseFirestore) {
    private val foodRef = firestore.collection("foods")

    suspend fun getAllFoods(): List<Food> {
        return try {
            val snapshot = foodRef.get().await()
            snapshot.toObjects(Food::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}