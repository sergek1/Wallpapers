package com.example.phonewalls.presentation.wallpaper

data class WallpaperState(
    val isLoading: Boolean = false,
    val wallpaper: String = "",
    val error: String = ""
)
