package com.example.phonewalls.presentation.wallpaper.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Result
import com.example.domain.usecase.GetPhotoByIdUseCase
import com.example.phonewalls.presentation.wallpaper.WallpaperState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class WallpaperViewModel(
    private val getPhotoByIdUseCase: GetPhotoByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(WallpaperState())
    val state: MutableState<WallpaperState> = _state

    init {
        savedStateHandle.get<String>("wallpaperId")?.let { wallpaperId ->
            getWallpaper(wallpaperId)
        }
    }

    private fun getWallpaper(id: String) {
        getPhotoByIdUseCase(id = id).onEach { result ->
            when (result) {
                is Result.Success -> {
                    _state.value = WallpaperState(wallpaper = result.data?.url ?: "")
                }

                is Result.Failure -> {
                    _state.value =
                        WallpaperState(error = result.message ?: "An unexpected error occurred")
                }

                is Result.Loading -> {
                    _state.value = WallpaperState(isLoading = true)
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}