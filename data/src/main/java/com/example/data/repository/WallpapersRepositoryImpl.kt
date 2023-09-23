package com.example.data.repository

import com.example.data.mapper.asDomainModel
import com.example.data.mapper.asDomainPhotoModel
import com.example.data.remote.WallpapersApi
import com.example.domain.model.Result
import com.example.domain.model.Wallpaper
import com.example.domain.model.WallpaperCategory
import com.example.domain.repository.WallpapersRepository

class WallpapersRepositoryImpl(
    private val api: WallpapersApi
) : WallpapersRepository {
    override suspend fun getCategories(): Result<List<WallpaperCategory>> {
        return try {
            val result = api.getCategories()
            Result.Success(result.asDomainModel())
        } catch (e: Exception) {
            Result.Failure(e.message.toString())
        }
    }

    override suspend fun getWallpaper(id: String): Result<Wallpaper> {
        return try {
            val result = api.getPhotoById(photoId = id)
            Result.Success(result.asDomainModel())
        } catch (e: Exception) {
            Result.Failure(e.message.toString())
        }
    }

    override suspend fun getWallpapersByCategoryId(id: String): Result<List<Wallpaper>> {
        return try {
            val result = api.getPhotosByTopic(topicId = id)
            Result.Success(result.asDomainPhotoModel())
        } catch (e: Exception) {
            Result.Failure(e.message.toString())
        }

    }
}