package com.example.phonewalls.presentation.category_wallpapers

import com.example.domain.model.Wallpaper

data class WallpapersState(
    val isLoading: Boolean = false,
    val wallpapers: List<Wallpaper> = emptyList(),
    val error: String = ""
)
