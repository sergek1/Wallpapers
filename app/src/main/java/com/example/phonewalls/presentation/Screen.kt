package com.example.phonewalls.presentation

sealed class Screen(val route: String) {
    object CategoriesScreen : Screen("categories_screen")
    object CategoryScreen : Screen("category_screen")
    object WallpaperScreen : Screen("wallpaper_screen")
}

