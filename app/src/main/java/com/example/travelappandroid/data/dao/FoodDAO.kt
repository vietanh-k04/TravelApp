package com.example.travelappandroid.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.travelappandroid.data.entity.FoodEntity

@Dao
interface FoodDAO {
    // 1. Lấy tất cả món ăn
    @Query("SELECT * FROM foods")
    suspend fun getAllFoods(): List<FoodEntity>

    // 2. Lấy theo id
    @Query("SELECT * FROM foods WHERE id = :foodId LIMIT 1")
    suspend fun getById(foodId: String): FoodEntity?

    // 3. Lấy theo tỉnh
    @Query("SELECT * FROM foods WHERE province = :province")
    suspend fun getByProvince(province: String): List<FoodEntity>

    // 4. Search theo tên món
    @Query("SELECT * FROM foods WHERE nameNoAccent LIKE '%' || :keyword || '%'")
    suspend fun search(keyword: String): List<FoodEntity>

    // 5. Insert list
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<FoodEntity>)

    // 6. Clear table
    @Query("DELETE FROM foods")
    suspend fun clear()
}