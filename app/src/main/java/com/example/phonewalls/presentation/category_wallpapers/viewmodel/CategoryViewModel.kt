package com.example.phonewalls.presentation.category_wallpapers.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Result
import com.example.domain.usecase.GetPhotosByCategoryUseCase
import com.example.phonewalls.presentation.category_wallpapers.WallpapersState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CategoryViewModel(
    private val getPhotosByCategoryUseCase: GetPhotosByCategoryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(WallpapersState())
    val state: MutableState<WallpapersState> = _state

    init {
        savedStateHandle.get<String>("topicId")?.let { topicId ->
            getWallpapers(topicId)
        }
    }

    private fun getWallpapers(id: String) {
        getPhotosByCategoryUseCase(id = id).onEach { result ->
            when (result) {
                is Result.Success -> {
                    _state.value = WallpapersState(wallpapers = result.data ?: emptyList())
                }

                is Result.Failure -> {
                    _state.value =
                        WallpapersState(error = result.message ?: "An unexpected error occurred")
                }

                is Result.Loading -> {
                    _state.value = WallpapersState(isLoading = true)
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}