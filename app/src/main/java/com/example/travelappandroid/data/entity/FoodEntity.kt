package com.example.travelappandroid.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
data class FoodEntity(
    @PrimaryKey val id: String = "",
    val name: String? = null,
    val province: String? = null,
    val description: String? = null,
    val thumbnail: String? = null,
    val recommendedPlacesJson: String? = null
)
