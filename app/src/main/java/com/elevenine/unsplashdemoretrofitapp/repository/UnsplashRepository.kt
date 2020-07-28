package com.elevenine.unsplashdemoretrofitapp.repository

import androidx.lifecycle.LiveData
import com.elevenine.unsplashdemoretrofitapp.data.db.UnsplashDao
import com.elevenine.unsplashdemoretrofitapp.data.model.UnsplashPhoto
import com.elevenine.unsplashdemoretrofitapp.data.network.UnsplashNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UnsplashRepository(
    private val unsplashNetworkDataSource: UnsplashNetworkDataSource,
    private val unsplashDao: UnsplashDao) {

    init{
        // Since the Repository does not have the lifecycle, we set the observing forever.
        // Every time, the fetchedPhotosList is updated from the Network (using the method
        // fetchPhotos()), the new list of photos will be saved to the UnsplashDatabase.
        unsplashNetworkDataSource.fetchedPhotosList.observeForever{ newPhotosList ->
            persistFetchedPhotos(newPhotosList)
        }
    }

    // save the fetched photos from network to DB
    private fun persistFetchedPhotos(newPhotosList: List<UnsplashPhoto>) {
        // Since there is no lifecycle for the repository, we can use the GlobalScope,
        // because the Repository will be closed only when the whole app is closed as well.
        GlobalScope.launch(Dispatchers.IO) {
            unsplashDao.insertAllPhotos(newPhotosList)
        }
    }

    // fetchPhotos() is a suspend function with coroutine context, since it must be main-safe.
    suspend fun fetchPhotos(){
        withContext(Dispatchers.IO) {
            unsplashNetworkDataSource.fetchPhotos()
        }
    }

    // getPhotosFromDb() has no need to be launched from coroutine manually, since the RoomDatabase
    // will handle a main-safe operation itself for the read operations.
    fun getPhotosFromDb(): LiveData<List<UnsplashPhoto>>{
        return unsplashDao.getAllPhotos()
    }

    fun getPhotoByIdFromDb(id: String): LiveData<UnsplashPhoto>{
        return unsplashDao.getPhotoById(id)
    }
}