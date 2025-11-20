package com.example.travelappandroid.di

import com.example.travelappandroid.data.local.BannerLocalDataSource
import com.example.travelappandroid.data.local.FoodLocalDataSource
import com.example.travelappandroid.data.local.ItineraryLocalDataSource
import com.example.travelappandroid.data.local.PlaceLocalDataSource
import com.example.travelappandroid.data.remote.BannerFirebaseDataSource
import com.example.travelappandroid.data.remote.FoodFirebaseDataSource
import com.example.travelappandroid.data.remote.ItineraryFirebaseDataSource
import com.example.travelappandroid.data.remote.PlaceFirebaseDataSource
import com.example.travelappandroid.data.repository.BannerRepository
import com.example.travelappandroid.data.repository.FoodRepository
import com.example.travelappandroid.data.repository.ItineraryRepository
import com.example.travelappandroid.data.repository.PlaceRepository
import com.google.android.datatransport.runtime.dagger.Provides
import jakarta.inject.Singleton

class RepositoryModule {
    @Provides
    @Singleton
    fun providePlaceRepository(
        local: PlaceLocalDataSource,
        remote: PlaceFirebaseDataSource
    ): PlaceRepository {
        return PlaceRepository(local, remote)
    }

    @Provides
    @Singleton
    fun provideFoodRepository(
        local: FoodLocalDataSource,
        remote: FoodFirebaseDataSource
    ): FoodRepository {
        return FoodRepository(local, remote)
    }

    @Provides
    @Singleton
    fun provideBannerRepository(
        local: BannerLocalDataSource,
        remote: BannerFirebaseDataSource
    ): BannerRepository {
        return BannerRepository(local, remote)
    }

    @Provides
    @Singleton
    fun provideItineraryRepository(
        local: ItineraryLocalDataSource,
        remote: ItineraryFirebaseDataSource
    ): ItineraryRepository {
        return ItineraryRepository(local, remote)
    }
}