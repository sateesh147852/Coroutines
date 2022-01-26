package com.coroutines.api

import com.coroutines.model.RetroPhoto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/photos")
    suspend fun getAllPhotos(): Response<List<RetroPhoto>>

}