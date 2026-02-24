package com.example.travelappandroid.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.travelappandroid.data.entity.FoodEntity

@Dao
interface FoodDAO {
    @Query("SELECT * FROM foods")
    suspend fun getAllFoods(): List<FoodEntity>

    @Query("SELECT * FROM foods WHERE id = :foodId LIMIT 1")
    suspend fun getById(foodId: String): FoodEntity?

    @Query("SELECT * FROM foods WHERE provinceNoAccent LIKE '%' || :province || '%'")
    suspend fun getByProvince(province: String): List<FoodEntity>

    @Query("SELECT * FROM foods LIMIT :count")
    suspend fun getTop(count: Int): List<FoodEntity>

    @Query("SELECT * FROM foods WHERE nameNoAccent LIKE '%' || :keyword || '%'")
    suspend fun search(keyword: String): List<FoodEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<FoodEntity>)

    @Query("DELETE FROM foods")
    suspend fun clear()
}