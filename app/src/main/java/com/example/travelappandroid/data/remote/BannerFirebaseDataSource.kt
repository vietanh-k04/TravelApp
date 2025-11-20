package com.example.travelappandroid.data.remote

import com.example.travelappandroid.data.model.Banner
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class BannerFirebaseDataSource(firestore: FirebaseFirestore) {
    private val bannerRef = firestore.collection("banners")

    suspend fun getAllBanners(): List<Banner> {
        return try {
            val snapshot = bannerRef.get().await()
            snapshot.toObjects(Banner::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}