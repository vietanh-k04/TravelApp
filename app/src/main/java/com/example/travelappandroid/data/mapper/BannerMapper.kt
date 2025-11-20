package com.example.travelappandroid.data.mapper

import com.example.travelappandroid.data.entity.BannerEntity
import com.example.travelappandroid.data.model.Banner

fun Banner.toEntity(): BannerEntity {
    return BannerEntity(
        id = id,
        image = image,
        title = title,
        placeId = placeId
    )
}

fun BannerEntity.toModel(): Banner {
    return Banner(
        id = id,
        image = image,
        title = title,
        placeId = placeId
    )
}