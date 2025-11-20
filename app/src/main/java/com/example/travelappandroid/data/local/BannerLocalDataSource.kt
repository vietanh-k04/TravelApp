package com.example.travelappandroid.data.local

import com.example.travelappandroid.data.dao.BannerDAO
import com.example.travelappandroid.data.mapper.toEntity
import com.example.travelappandroid.data.mapper.toModel
import com.example.travelappandroid.data.model.Banner

class BannerLocalDataSource(private val bannerDAO: BannerDAO) {
    suspend fun getAllBanners(): List<Banner> {
        return bannerDAO.getAllBanners().map { it.toModel() }
    }

    suspend fun getBannerById(id: String): Banner? {
        return bannerDAO.getById(id)?.toModel()
    }

    suspend fun bannerDAO(placeId: String): List<Banner> {
        return bannerDAO.getByPlace(placeId).map { it.toModel() }
    }

    suspend fun saveBanners(list: List<Banner>) {
        bannerDAO.clear()
        bannerDAO.insertAll(list.map { it.toEntity() })
    }
}