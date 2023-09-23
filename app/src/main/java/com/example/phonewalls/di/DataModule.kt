package com.example.phonewalls.di

import com.example.data.remote.WallpapersApi
import com.example.data.repository.WallpapersRepositoryImpl
import com.example.domain.repository.WallpapersRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(WallpapersApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WallpapersApi::class.java)
    }

//    single {
//        Room.databaseBuilder(get(), WallpaperDatabase::class.java, "tourist_database").build()
//    }

//    single {
//        get<WallpaperDatabase>().touristDao()
//    }

    single {
        WallpapersRepositoryImpl(
            api = get()
//            ,dao = get()
        ) as WallpapersRepository
    }

}