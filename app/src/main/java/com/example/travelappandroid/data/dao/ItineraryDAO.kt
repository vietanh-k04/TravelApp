package com.example.travelappandroid.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.travelappandroid.data.entity.ItineraryEntity

@Dao
interface ItineraryDAO {
    // 1. Lấy toàn bộ hành trình
    @Query("SELECT * FROM itineraries")
    suspend fun getAllItineraries(): List<ItineraryEntity>

    // 2. Lấy theo id
    @Query("SELECT * FROM itineraries WHERE id = :itineraryId LIMIT 1")
    suspend fun getById(itineraryId: String): ItineraryEntity?

    // 3. Search theo title
    @Query("SELECT * FROM itineraries WHERE title LIKE '%' || :keyword || '%'")
    suspend fun search(keyword: String): List<ItineraryEntity>

    // 4. Insert list
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ItineraryEntity>)

    // 5. Clear table
    @Query("DELETE FROM itineraries")
    suspend fun clear()
}