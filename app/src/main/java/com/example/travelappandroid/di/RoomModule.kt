package com.example.travelappandroid.di

import android.app.Application
import androidx.room.Room
import com.example.travelappandroid.data.dao.BannerDAO
import com.example.travelappandroid.data.dao.FoodDAO
import com.example.travelappandroid.data.dao.ItineraryDAO
import com.example.travelappandroid.data.dao.PlaceDAO
import com.example.travelappandroid.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "travel_app.db" )
            .fallbackToDestructiveMigration(false)  // Xoá DB cũ nếu nâng version
            .build()
    }

    @Provides
    @Singleton
    fun providePlaceDAO(db: AppDatabase): PlaceDAO = db.placeDAO()

    @Provides
    @Singleton
    fun provideFoodDAO(db: AppDatabase): FoodDAO = db.FoodDAO()

    @Provides
    @Singleton
    fun provideBannerDAO(db: AppDatabase): BannerDAO = db.BannerDAO()

    @Provides
    @Singleton
    fun provideItineraryDAO(db: AppDatabase): ItineraryDAO = db.ItineraryDAO()
}