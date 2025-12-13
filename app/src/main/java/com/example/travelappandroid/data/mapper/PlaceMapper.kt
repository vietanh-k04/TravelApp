package com.example.travelappandroid.data.mapper

import com.example.travelappandroid.data.entity.PlaceEntity
import com.example.travelappandroid.data.model.Place
import com.example.travelappandroid.utils.toNoAccent
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

fun Place.toEntity(gson: Gson): PlaceEntity {
    return PlaceEntity(
        id = id,
        name = name,
        region = region,
        province = province,
        category = category,
        thumbnail = thumbnail,
        galleryJson = gson.toJson(gallery),
        description = description,
        history = history,
        tipsJson = gson.toJson(tips),
        ticketPrice = ticketPrice,
        openingTime = openingTime,
        latitude = latitude,
        longitude = longitude,
        rating = rating,
        isTrending = isTrending,
        nameNoAccent = name?.toNoAccent(),
        regionNoAccent = region?.toNoAccent(),
        provinceNoAccent = province?.toNoAccent()

    )
}

fun PlaceEntity.toModel(gson: Gson): Place {
    val listType = object : TypeToken<List<String>>() {}.type

    return Place(
        id = id,
        name = name,
        region = region,
        province = province,
        category = category,
        thumbnail = thumbnail,
        gallery = gson.fromJson(galleryJson, listType),
        description = description,
        history = history,
        tips = gson.fromJson(tipsJson, listType),
        ticketPrice = ticketPrice,
        openingTime = openingTime,
        latitude = latitude,
        longitude = longitude,
        rating = rating,
        isTrending = isTrending
    )
}