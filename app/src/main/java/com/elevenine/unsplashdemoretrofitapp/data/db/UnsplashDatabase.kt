package com.elevenine.unsplashdemoretrofitapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elevenine.unsplashdemoretrofitapp.data.model.UnsplashPhoto

@Database(entities = [UnsplashPhoto::class], version = 1, exportSchema = false)
abstract class UnsplashDatabase : RoomDatabase() {

    abstract val unsplashDao: UnsplashDao

    companion object{

        @Volatile
        private var INSTANCE: UnsplashDatabase? = null

        fun getInstance(context: Context): UnsplashDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UnsplashDatabase::class.java,
                        "unsplash_database"
                    ).build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}