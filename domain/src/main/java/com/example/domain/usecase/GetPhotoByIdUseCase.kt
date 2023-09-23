package com.example.domain.usecase

import com.example.domain.model.Result
import com.example.domain.model.Wallpaper
import com.example.domain.repository.WallpapersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetPhotoByIdUseCase(private val repository: WallpapersRepository) {
    operator fun invoke(id: String): Flow<Result<Wallpaper>> = flow {
        try {
            emit(Result.Loading())
            val wallpaper = repository.getWallpaper(id)
            emit(Result.Success(wallpaper.data))
        } catch (e: IOException) {
            emit(Result.Failure("Couldn't reach server. Check your internet connection"))
        } catch (e: Exception) {
            emit(Result.Failure(e.message.toString()))
        }
    }
}