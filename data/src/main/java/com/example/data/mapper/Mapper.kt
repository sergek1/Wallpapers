package com.example.data.mapper

import com.example.data.remote.dto.CategoryDto
import com.example.data.remote.dto.PhotoDto
import com.example.domain.model.Wallpaper
import com.example.domain.model.WallpaperCategory

fun CategoryDto.asDomainModel(): WallpaperCategory {
    return WallpaperCategory(
        id = id,
        title = title,
        url = coverPhoto?.urls?.regular
    )
}

fun PhotoDto.asDomainModel(): Wallpaper {
    return Wallpaper(
        id = id,
        description = description ?: altDescription,
        url = urls?.regular
    )
}

fun List<CategoryDto>.asDomainModel(): List<WallpaperCategory> {
    return map {
        WallpaperCategory(
            id = it.id,
            title = it.title,
            url = it.coverPhoto?.urls?.regular
        )
    }
}

fun List<PhotoDto>.asDomainPhotoModel(): List<Wallpaper> {
    return map {
        Wallpaper(
            id = it.id,
            description = it.description ?: it.altDescription,
            url = it.urls?.regular
        )
    }
}