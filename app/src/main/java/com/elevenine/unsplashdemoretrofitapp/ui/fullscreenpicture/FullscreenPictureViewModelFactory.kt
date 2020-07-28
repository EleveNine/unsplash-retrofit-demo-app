package com.elevenine.unsplashdemoretrofitapp.ui.fullscreenpicture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elevenine.unsplashdemoretrofitapp.repository.UnsplashRepository

class FullscreenPictureViewModelFactory (
    private val repository: UnsplashRepository,
    private val photoId: String
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FullscreenPictureViewModel::class.java)) {
            return FullscreenPictureViewModel(repository, photoId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}