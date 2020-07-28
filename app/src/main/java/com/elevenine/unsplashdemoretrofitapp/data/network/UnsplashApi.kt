package com.elevenine.unsplashdemoretrofitapp.data.network

import com.elevenine.unsplashdemoretrofitapp.data.model.UnsplashPhoto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("photos")
    fun getPhotos() : Deferred<Response<List<UnsplashPhoto>>>
}
