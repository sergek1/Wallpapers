package com.example.domain.usecase

import com.example.domain.model.Result
import com.example.domain.model.Wallpaper
import com.example.domain.model.WallpaperCategory
import com.example.domain.repository.WallpapersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetPhotosByCategoryUseCase(private val repository: WallpapersRepository) {
    operator fun invoke(id: String): Flow<Result<List<Wallpaper>>> = flow {
        try {
            emit(Result.Loading())
            val photos = repository.getWallpapersByCategoryId(id)
            emit(Result.Success(photos.data))
        } catch (e: IOException) {
            emit(Result.Failure("Couldn't reach server. Check your internet connection"))
        } catch (e: Exception) {
            emit(Result.Failure(e.message.toString()))
        }
    }
}