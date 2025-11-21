package com.example.travelappandroid.di

import com.example.travelappandroid.data.local.BannerLocalDataSource
import com.example.travelappandroid.data.local.FoodLocalDataSource
import com.example.travelappandroid.data.local.ItineraryLocalDataSource
import com.example.travelappandroid.data.local.PlaceLocalDataSource
import com.example.travelappandroid.data.remote.BannerFirebaseDataSource
import com.example.travelappandroid.data.remote.FoodFirebaseDataSource
import com.example.travelappandroid.data.remote.ItineraryFirebaseDataSource
import com.example.travelappandroid.data.remote.PlaceFirebaseDataSource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import jakarta.inject.Singleton

@Module
class DataSourceModule {
    @Provides
    @Singleton
    fun providePlaceLocal(local: PlaceLocalDataSource): PlaceLocalDataSource = local

    @Provides
    @Singleton
    fun provideFoodLocal(local: FoodLocalDataSource): FoodLocalDataSource = local

    @Provides
    @Singleton
    fun provideBannerLocal(local: BannerLocalDataSource): BannerLocalDataSource = local

    @Provides
    @Singleton
    fun provideItineraryLocal(local: ItineraryLocalDataSource): ItineraryLocalDataSource = local

    @Provides
    @Singleton
    fun providePlaceRemote(firestore: FirebaseFirestore): PlaceFirebaseDataSource = PlaceFirebaseDataSource(firestore)

    @Provides
    @Singleton
    fun provideFoodRemote(firestore: FirebaseFirestore): FoodFirebaseDataSource = FoodFirebaseDataSource(firestore)

    @Provides
    @Singleton
    fun provideBannerRemote(firestore: FirebaseFirestore): BannerFirebaseDataSource = BannerFirebaseDataSource(firestore)

    @Provides
    @Singleton
    fun provideItineraryRemote(firestore: FirebaseFirestore): ItineraryFirebaseDataSource = ItineraryFirebaseDataSource(firestore)
}