package com.elevenine.unsplashdemoretrofitapp.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elevenine.unsplashdemoretrofitapp.data.model.UnsplashPhoto

class UnsplashNetworkDataSource(private val api: UnsplashApi) : BaseNetworkDataSource() {

    private val _fetchedPhotosList = MutableLiveData<List<UnsplashPhoto>>()
    val fetchedPhotosList: LiveData<List<UnsplashPhoto>>
        get() = _fetchedPhotosList

    suspend fun fetchPhotos(){
        // safeApiCall() is a method, defined in BaseNetworkDataSource
        val photoResponse = safeApiCall(
            call = {api.getPhotos().await()},
            errorMessage = "Error fetching photos from Unsplash"
        )

        _fetchedPhotosList.postValue(photoResponse?.toMutableList())
    }
}