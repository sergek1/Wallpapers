package com.example.domain.repository

import com.example.domain.model.Wallpaper
import com.example.domain.model.WallpaperCategory
import com.example.domain.model.Result

interface WallpapersRepository {

    suspend fun getCategories(): Result<List<WallpaperCategory>>

    suspend fun getWallpaper(id: String): Result<Wallpaper>

    suspend fun getWallpapersByCategoryId(id: String): Result<List<Wallpaper>>

}