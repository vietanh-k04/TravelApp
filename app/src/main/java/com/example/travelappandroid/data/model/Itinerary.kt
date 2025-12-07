package com.example.travelappandroid.data.model

data class Itinerary(
    val id: String = "",
    val title: String? = null,
    val duration: String? = null,
    val places: List<String>? = null,
    val description: String? = null
)
