package com.example.phonewalls.di

import com.example.phonewalls.presentation.categories.viewmodel.CategoriesViewModel
import com.example.phonewalls.presentation.category_wallpapers.viewmodel.CategoryViewModel
import com.example.phonewalls.presentation.wallpaper.viewmodel.WallpaperViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        CategoriesViewModel(
            getCategoriesUseCase = get()
        )
    }

    viewModel {
        CategoryViewModel(
            getPhotosByCategoryUseCase = get(),
            savedStateHandle = get()
        )
    }

    viewModel {
        WallpaperViewModel(
            getPhotoByIdUseCase = get(),
            savedStateHandle = get()
        )
    }
}