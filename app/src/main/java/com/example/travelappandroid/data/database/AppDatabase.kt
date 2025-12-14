package com.example.travelappandroid.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.travelappandroid.data.dao.BannerDAO
import com.example.travelappandroid.data.dao.FoodDAO
import com.example.travelappandroid.data.dao.ItineraryDAO
import com.example.travelappandroid.data.dao.PlaceDAO
import com.example.travelappandroid.data.entity.BannerEntity
import com.example.travelappandroid.data.entity.FoodEntity
import com.example.travelappandroid.data.entity.ItineraryEntity
import com.example.travelappandroid.data.entity.PlaceEntity

@Database(
    entities = [
        PlaceEntity::class,
        FoodEntity::class,
        BannerEntity::class,
        ItineraryEntity::class, ],
    version = 8,
    exportSchema = false
)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun placeDAO() : PlaceDAO
    abstract fun FoodDAO() : FoodDAO
    abstract fun BannerDAO(): BannerDAO
    abstract fun ItineraryDAO(): ItineraryDAO
}