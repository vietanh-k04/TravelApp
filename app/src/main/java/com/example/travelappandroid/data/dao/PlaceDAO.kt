package com.example.travelappandroid.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.travelappandroid.data.entity.PlaceEntity

@Dao
interface PlaceDAO {
    // 1. Lấy tất cả
    @Query("SELECT * FROM places")
    suspend fun getAllPlaces() : List<PlaceEntity>

    // 2. Lấy theo id
    @Query("SELECT * FROM places WHERE id = :placeId LIMIT 1")
    suspend fun getById(placeId: String): PlaceEntity

    // 3. Trending
    @Query("SELECT * FROM places WHERE isTrending = 1 ORDER BY rating DESC")
    suspend fun getTrending(): List<PlaceEntity>

    // 4. Theo vùng (region)
    @Query("SELECT * FROM places WHERE regionNoAccent = :region")
    suspend fun getByRegion(region: String): List<PlaceEntity>

    // 5. Theo tỉnh/thành
    @Query("SELECT * FROM places WHERE provinceNoAccent = :province")
    suspend fun getByProvince(province: String): List<PlaceEntity>

    // 6. Theo category
    @Query("SELECT * FROM places WHERE category = :category")
    suspend fun getByCategory(category: String): List<PlaceEntity>

    // 7. Search theo tên
    @Query("SELECT * FROM places WHERE nameNoAccent LIKE '%' || :keyword || '%'")
    suspend fun search(keyword: String): List<PlaceEntity>

    // 8. Insert list
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<PlaceEntity>)

    // 9. Clear table
    @Query("DELETE FROM places")
    suspend fun clear()

    @Query("SELECT * FROM places WHERE rating >= :rating ORDER BY rating DESC")
    suspend fun getRecommended(rating: Double) : List<PlaceEntity>
}