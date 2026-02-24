package com.example.travelappandroid.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.travelappandroid.data.entity.BannerEntity

@Dao
interface BannerDAO {
    @Query("SELECT * FROM banners")
    suspend fun getAllBanners(): List<BannerEntity>

    @Query("SELECT * FROM banners WHERE id = :bannerId LIMIT 1")
    suspend fun getById(bannerId: String): BannerEntity?

    @Query("SELECT * FROM banners WHERE placeId = :placeId")
    suspend fun getByPlace(placeId: String): List<BannerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<BannerEntity>)

    @Query("DELETE FROM banners")
    suspend fun clear()
}