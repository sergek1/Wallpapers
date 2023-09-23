package com.example.phonewalls.presentation.categories

import com.example.domain.model.WallpaperCategory

data class CategoriesState(
    val isLoading: Boolean = false,
    val categories: List<WallpaperCategory> = emptyList(),
    val error: String = ""
)
