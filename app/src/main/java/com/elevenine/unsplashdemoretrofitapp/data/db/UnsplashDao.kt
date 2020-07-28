package com.elevenine.unsplashdemoretrofitapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elevenine.unsplashdemoretrofitapp.data.model.UnsplashPhoto

@Dao
interface UnsplashDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(photo: UnsplashPhoto)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllPhotos(photoList: List<UnsplashPhoto>)

    @Query("SELECT * FROM unsplash_photo_table ORDER BY id DESC")
    fun getAllPhotos(): LiveData<List<UnsplashPhoto>>

    @Query("SELECT * FROM unsplash_photo_table WHERE id=:id")
    fun getPhotoById(id: String): LiveData<UnsplashPhoto>
}