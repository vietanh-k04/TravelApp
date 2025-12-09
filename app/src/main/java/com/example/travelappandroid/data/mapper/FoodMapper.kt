package com.example.travelappandroid.data.mapper

import com.example.travelappandroid.data.entity.FoodEntity
import com.example.travelappandroid.data.model.Food
import com.example.travelappandroid.utils.toNoAccent
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

fun Food.toEntity(gson: Gson): FoodEntity {
    return FoodEntity(
        id = id,
        name = name,
        province = province,
        description = description,
        thumbnail = thumbnail,
        recommendedPlacesJson = gson.toJson(recommendedPlaces),
        nameNoAccent = name?.toNoAccent()
    )
}

fun FoodEntity.toModel(gson: Gson): Food {
    val listType = object : TypeToken<List<String>>() {}.type

    return Food(
        id = id,
        name = name,
        province = province,
        description = description,
        thumbnail = thumbnail,
        recommendedPlaces = gson.fromJson(recommendedPlacesJson, listType)
    )
}