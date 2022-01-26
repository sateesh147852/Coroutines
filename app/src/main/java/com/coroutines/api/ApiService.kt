package com.coroutines.api

import com.coroutines.model.RetroPhoto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/photos")
    fun getAllPhotos(): Deferred<Response<List<RetroPhoto>>>

}