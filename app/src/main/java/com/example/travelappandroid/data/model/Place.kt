package com.example.travelappandroid.data.model

data class Place(
    val id: String? = null,
    val name: String? = null,
    val region: String? = null,
    val province: String? = null,
    val category: String? = null,

    val thumbnail: String? = null,
    val gallery: List<String>? = null,

    val description: String? = null,
    val history: String? = null,
    val tips: List<String>? = null,

    val ticketPrice: String? = null,
    val openingTime: String? = null,

    val latitude: Double? = null,
    val longitude: Double? = null,

    val rating: Double? = null,
    val isTrending: Boolean? = null
)