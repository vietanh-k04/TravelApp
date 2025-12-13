package com.example.travelappandroid.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itineraries")
data class ItineraryEntity(
    @PrimaryKey val id: String = "",
    val title: String? = null,
    val province: String? = null,
    val duration: String? = null,
    val placesJson: String? = null,
    val description: String? = null,
    val thumbnail: String? = null,
    val nameNoAccent: String? = null,
    val provinceNoAccent: String? = null
)
