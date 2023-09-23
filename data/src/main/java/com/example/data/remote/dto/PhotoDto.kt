package com.example.data.remote.dto

import com.squareup.moshi.Json

data class PhotoDto(
    val id: String,
    val description: String?,
    @field:Json(name = "alt_description") val altDescription: String?,
    val urls: Urls?
)

data class Urls(
    val regular: String?
)
