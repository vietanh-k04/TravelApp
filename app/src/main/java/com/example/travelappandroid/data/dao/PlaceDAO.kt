package com.example.travelappandroid.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.travelappandroid.data.entity.PlaceEntity

@Dao
interface PlaceDAO {
    @Query("SELECT * FROM places")
    suspend fun getAllPlaces() : List<PlaceEntity>

    @Query("SELECT * FROM places WHERE id = :placeId LIMIT 1")
    suspend fun getById(placeId: String): PlaceEntity

    @Query("SELECT * FROM places WHERE isTrending = 1 ORDER BY rating DESC")
    suspend fun getTrending(): List<PlaceEntity>

    @Query("SELECT * FROM places WHERE regionNoAccent = :region")
    suspend fun getByRegion(region: String): List<PlaceEntity>

    @Query("SELECT * FROM places WHERE provinceNoAccent LIKE '%' || :province || '%'")
    suspend fun getByProvince(province: String): List<PlaceEntity>

    @Query("SELECT * FROM places WHERE category = :category")
    suspend fun getByCategory(category: String): List<PlaceEntity>

    @Query("SELECT * FROM places WHERE nameNoAccent LIKE '%' || :keyword || '%'")
    suspend fun search(keyword: String): List<PlaceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<PlaceEntity>)

    @Query("DELETE FROM places")
    suspend fun clear()

    @Query("SELECT * FROM places WHERE rating >= :rating ORDER BY rating DESC")
    suspend fun getRecommended(rating: Double) : List<PlaceEntity>
}