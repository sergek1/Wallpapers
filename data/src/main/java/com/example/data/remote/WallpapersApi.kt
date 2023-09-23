package com.example.data.remote

import com.example.data.remote.dto.CategoryDto
import com.example.data.remote.dto.PhotoDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WallpapersApi {

    @GET("topics")
    suspend fun getCategories(
        @Query("client_id") apikey: String = API_KEY
    ): List<CategoryDto>

    @GET("topics/{topic_id}/photos")
    suspend fun getPhotosByTopic(
        @Path("topic_id") topicId: String,
        @Query("client_id") apikey: String = API_KEY
    ): List<PhotoDto>

    @GET("photos/{photo_id}")
    suspend fun getPhotoById(
        @Path("photo_id") photoId: String,
        @Query("client_id") apikey: String = API_KEY
    ): PhotoDto

    companion object {
        const val API_KEY = "sP14OA8qhRaeNaXfCodu6y65LWGOyKz_FdVgE-iG_b0"
        const val BASE_URL = "https://api.unsplash.com/"
    }
}