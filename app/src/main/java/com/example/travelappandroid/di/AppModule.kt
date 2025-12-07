package com.example.travelappandroid.di

import android.content.Context
import androidx.room.Room
import com.example.travelappandroid.data.database.AppDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Firestore
    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    // Room database
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "travel.db").build()

    @Provides
    fun providePlaceDao(db: AppDatabase) = db.placeDAO()

    @Provides
    fun provideFoodDao(db: AppDatabase) = db.FoodDAO()

    @Provides
    fun provideBannerDao(db: AppDatabase) = db.BannerDAO()

    @Provides
    fun provideItineraryDao(db: AppDatabase) = db.ItineraryDAO()
}