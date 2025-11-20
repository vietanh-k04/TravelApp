package com.example.travelappandroid.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banners")
data class BannerEntity(
    @PrimaryKey val id: String? = null,
    val image: String? = null,
    val title: String? = null,
    val placeId: String? = null
)
