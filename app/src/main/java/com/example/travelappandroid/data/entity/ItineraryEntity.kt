package com.example.travelappandroid.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itineraries")
data class ItineraryEntity(
    @PrimaryKey val id: String? = null,
    val title: String? = null,
    val duration: String? = null,
    val placesJson: String? = null,
    val description: String? = null
)
