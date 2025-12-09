package com.example.travelappandroid.data.model

data class Food(
    val id: String = "",
    val name: String? = null,
    val province: String? = null,
    val description: String? = null,
    val thumbnail: String? = null,
    val recommendedPlaces: List<String>? = null,
)
