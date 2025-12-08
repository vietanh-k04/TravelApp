package com.example.travelappandroid.data.mapper

import com.example.travelappandroid.data.entity.ItineraryEntity
import com.example.travelappandroid.data.entity.PlaceEntity
import com.example.travelappandroid.data.model.Itinerary
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

fun Itinerary.toEntity(gson: Gson): ItineraryEntity {
    return ItineraryEntity(
        id = id,
        title = title,
        duration = duration,
        placesJson = gson.toJson(places),
        description = description,
        thumbnail = thumbnail
    )
}

fun ItineraryEntity.toModel(gson: Gson): Itinerary {
    val listType = object : TypeToken<List<String>>() {}.type

    return Itinerary(
        id = id,
        title = title,
        duration = duration,
        places = gson.fromJson(placesJson, listType),
        description = description,
        thumbnail = thumbnail
    )
}

fun fromRemote(model: Itinerary, firstPlace: PlaceEntity?): Itinerary {
    return model.copy(
        thumbnail = firstPlace?.thumbnail ?: firstPlace?.thumbnail ?: ""
    )
}