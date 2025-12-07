package com.example.travelappandroid.di

import com.example.travelappandroid.data.dao.BannerDAO
import com.example.travelappandroid.data.dao.FoodDAO
import com.example.travelappandroid.data.dao.ItineraryDAO
import com.example.travelappandroid.data.dao.PlaceDAO
import com.example.travelappandroid.data.local.BannerLocalDataSource
import com.example.travelappandroid.data.local.FoodLocalDataSource
import com.example.travelappandroid.data.local.ItineraryLocalDataSource
import com.example.travelappandroid.data.local.PlaceLocalDataSource
import com.example.travelappandroid.data.remote.BannerFirebaseDataSource
import com.example.travelappandroid.data.remote.FoodFirebaseDataSource
import com.example.travelappandroid.data.remote.ItineraryFirebaseDataSource
import com.example.travelappandroid.data.remote.PlaceFirebaseDataSource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun providePlaceLocal(ds: PlaceDAO, gson: Gson) =
        PlaceLocalDataSource(ds, gson)

    @Provides
    @Singleton
    fun provideFoodLocal(ds: FoodDAO, gson: Gson) =
        FoodLocalDataSource(ds, gson)

    @Provides
    @Singleton
    fun provideBannerLocal(ds: BannerDAO) =
        BannerLocalDataSource(ds)

    @Provides
    @Singleton
    fun provideItineraryLocal(ds: ItineraryDAO, gson: Gson) =
        ItineraryLocalDataSource(ds, gson)

    @Provides
    @Singleton
    fun providePlaceRemote(firestore: FirebaseFirestore) =
        PlaceFirebaseDataSource(firestore)

    @Provides
    @Singleton
    fun provideFoodRemote(firestore: FirebaseFirestore) =
        FoodFirebaseDataSource(firestore)

    @Provides
    @Singleton
    fun provideBannerRemote(firestore: FirebaseFirestore) =
        BannerFirebaseDataSource(firestore)

    @Provides
    @Singleton
    fun provideItineraryRemote(firestore: FirebaseFirestore) =
        ItineraryFirebaseDataSource(firestore)
}