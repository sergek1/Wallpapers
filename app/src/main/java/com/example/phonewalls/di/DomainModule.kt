package com.example.phonewalls.di

import com.example.domain.usecase.GetCategoriesUseCase
import com.example.domain.usecase.GetPhotoByIdUseCase
import com.example.domain.usecase.GetPhotosByCategoryUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCategoriesUseCase(repository = get()) }
    factory { GetPhotosByCategoryUseCase(repository = get()) }
    factory { GetPhotoByIdUseCase(repository = get()) }
}