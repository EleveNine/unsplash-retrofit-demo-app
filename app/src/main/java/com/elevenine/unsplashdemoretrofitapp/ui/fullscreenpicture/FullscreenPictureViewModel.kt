package com.elevenine.unsplashdemoretrofitapp.ui.fullscreenpicture

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elevenine.unsplashdemoretrofitapp.repository.UnsplashRepository

class FullscreenPictureViewModel(
    private val repository: UnsplashRepository,
    private val photoId: String) : ViewModel() {

    var unsplashPhotoLiveData = repository.getPhotoByIdFromDb(photoId)
}