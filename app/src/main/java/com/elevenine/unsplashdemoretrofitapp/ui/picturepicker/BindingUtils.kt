package com.elevenine.unsplashdemoretrofitapp.ui.picturepicker

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.elevenine.unsplashdemoretrofitapp.data.model.UnsplashPhoto
import com.squareup.picasso.Picasso

@BindingAdapter("itemImagePreview")
fun ImageView.setItemImage(item: UnsplashPhoto?){
    if (item != null){
        Picasso.get()
            .load(item.urls.small).into(this)
    }
}

@BindingAdapter("itemAltDescription")
fun TextView.setAltDescription(item: UnsplashPhoto?){
    if(item != null){
        this.text = item.altDescription
    }
}