package com.elevenine.unsplashdemoretrofitapp.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "unsplash_photo_table")
data class UnsplashPhoto(
    @SerializedName("alt_description")
    val altDescription: String,
    val color: String,
    val description: String,
    val height: Int,
    @PrimaryKey val id: String,
    val likes: Int,
    @Embedded(prefix = "link_") val links: Links,
    @Embedded(prefix = "url_") val urls: Urls,
    val width: Int
)