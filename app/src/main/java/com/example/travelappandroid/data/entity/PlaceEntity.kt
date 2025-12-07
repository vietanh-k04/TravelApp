package com.example.travelappandroid.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "places")
data class PlaceEntity(
    @PrimaryKey val id: String = "",
    val name: String? = null,
    val region: String? = null,
    val province: String? = null,
    val category: String? = null,

    val thumbnail: String? = null,
    val galleryJson: String? = null,

    val description: String? = null,
    val history: String? = null,
    val tipsJson: String? = null,

    val ticketPrice: String? = null,
    val openingTime: String? = null,

    val latitude: Double? = null,
    val longitude: Double? = null,

    val rating: Double? = null,
    val isTrending: Boolean? = null
)
