package com.example.travelappandroid.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.travelappandroid.data.entity.ItineraryEntity
import com.example.travelappandroid.data.entity.PlaceEntity

@Dao
interface ItineraryDAO {
    @Query("SELECT * FROM itineraries")
    suspend fun getAllItineraries(): List<ItineraryEntity>

    @Query("SELECT * FROM itineraries WHERE id = :itineraryId LIMIT 1")
    suspend fun getById(itineraryId: String): ItineraryEntity?

    @Query("SELECT * FROM itineraries WHERE nameNoAccent LIKE '%' || :keyword || '%'")
    suspend fun search(keyword: String): List<ItineraryEntity>

    @Query("SELECT * FROM itineraries WHERE provinceNoAccent LIKE '%' || :province || '%'")
    suspend fun getItineraryByProvince(province: String): List<ItineraryEntity>

    @Query("SELECT * FROM itineraries LIMIT :count")
    suspend fun getTop(count: Int): List<ItineraryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ItineraryEntity>)

    @Query("DELETE FROM itineraries")
    suspend fun clear()
}