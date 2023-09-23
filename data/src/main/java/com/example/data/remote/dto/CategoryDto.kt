package com.example.data.remote.dto

import com.squareup.moshi.Json

data class CategoryDto(
    val id: String?,
    val title: String?,
    @field:Json(name = "cover_photo") val coverPhoto: CoverPhotoDto?
)

data class CoverPhotoDto(
    val urls: UrlsDto?
)

data class UrlsDto(
    val regular: String?
)

