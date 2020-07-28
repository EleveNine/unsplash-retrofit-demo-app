package com.elevenine.unsplashdemoretrofitapp.ui.picturepicker

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.elevenine.unsplashdemoretrofitapp.data.model.UnsplashPhoto
import com.elevenine.unsplashdemoretrofitapp.repository.UnsplashRepository
import kotlinx.coroutines.launch

class PicturePickerViewModel(
    private val unsplashRepository: UnsplashRepository,
    application: Application) : AndroidViewModel(application) {

    var photosLiveData : LiveData<List<UnsplashPhoto>> = unsplashRepository.getPhotosFromDb()

    init {
        /*val unsplashRepository =
            UnsplashRepository(networkDataSource, databaseDao)*/

        Log.d("PicturePickerViewModel", "fetchPhotos() called")
        viewModelScope.launch {
            // fetchPhotos() function will automatically suspend the action of the coroutine to let main thread continue its operation
            unsplashRepository.fetchPhotos()
        }
    }

    // Navigates to the fragment_detail
    private val _navigateToFullscreenPicture = MutableLiveData<String>()
    val navigateToFullscreenPicture
        get() = _navigateToFullscreenPicture

    fun onPictureClicked(id: String) {
        _navigateToFullscreenPicture.value = id
    }

    fun onFullscreenPictureNavigated() {
        _navigateToFullscreenPicture.value = null
    }
}