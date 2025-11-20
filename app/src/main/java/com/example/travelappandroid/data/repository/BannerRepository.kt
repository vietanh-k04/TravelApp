package com.example.travelappandroid.data.repository

import com.example.travelappandroid.data.local.BannerLocalDataSource
import com.example.travelappandroid.data.model.Banner
import com.example.travelappandroid.data.remote.BannerFirebaseDataSource

class BannerRepository(private val local: BannerLocalDataSource, private val remote: BannerFirebaseDataSource) {
    suspend fun getAllBanners(): List<Banner> {
        return local.getAllBanners()
    }

    suspend fun getBannerById(id: String): Banner? {
        return local.getBannerById(id)
    }

    suspend fun getBannersByPlace(placeId: String): List<Banner> {
        return local.bannerDAO(placeId)
    }

    suspend fun refreshBanners() {
        val list = remote.getAllBanners()
        if (list.isNotEmpty()) {
            local.saveBanners(list)
        }
    }
}