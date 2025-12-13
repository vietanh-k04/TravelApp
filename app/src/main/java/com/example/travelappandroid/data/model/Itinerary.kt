package com.example.travelappandroid.data.model

import com.google.firebase.firestore.Exclude

data class Itinerary(
    val id: String = "",
    val title: String? = null,
    val province: String? = null,
    val duration: String? = null,
    val places: List<String>? = null,
    val description: String? = null,
    var thumbnail: String? = null
)
