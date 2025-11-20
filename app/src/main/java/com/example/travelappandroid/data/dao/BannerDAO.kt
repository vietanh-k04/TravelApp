package com.example.travelappandroid.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.travelappandroid.data.entity.BannerEntity

@Dao
interface BannerDAO {
    // 1. Lấy toàn bộ banner
    @Query("SELECT * FROM banners")
    suspend fun getAllBanners(): List<BannerEntity>

    // 2. Lấy theo id
    @Query("SELECT * FROM banners WHERE id = :bannerId LIMIT 1")
    suspend fun getById(bannerId: String): BannerEntity?

    // 3. Lấy banner theo địa điểm (nếu muốn lọc ở UI)
    @Query("SELECT * FROM banners WHERE placeId = :placeId")
    suspend fun getByPlace(placeId: String): List<BannerEntity>

    // 4. Insert list
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<BannerEntity>)

    // 5. Clear table
    @Query("DELETE FROM banners")
    suspend fun clear()
}