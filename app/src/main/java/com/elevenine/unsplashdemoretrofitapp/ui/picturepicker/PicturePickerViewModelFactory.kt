package com.elevenine.unsplashdemoretrofitapp.ui.picturepicker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elevenine.unsplashdemoretrofitapp.repository.UnsplashRepository

class PicturePickerViewModelFactory(
    private val repository: UnsplashRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PicturePickerViewModel::class.java)) {
            return PicturePickerViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}