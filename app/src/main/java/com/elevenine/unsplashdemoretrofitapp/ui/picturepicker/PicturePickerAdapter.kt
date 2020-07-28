package com.elevenine.unsplashdemoretrofitapp.ui.picturepicker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elevenine.unsplashdemoretrofitapp.data.model.UnsplashPhoto
import com.elevenine.unsplashdemoretrofitapp.databinding.RecyclerViewImageItemBinding

class PicturePickerAdapter(val clickListener: PicturePickerListener) :
    ListAdapter<UnsplashPhoto, PicturePickerAdapter.ViewHolder>(PicturePickerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewImageItemBinding.inflate(
            layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }


    class ViewHolder(val binding: RecyclerViewImageItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(clickListener: PicturePickerListener, item: UnsplashPhoto){
            binding.item = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}

class PicturePickerDiffCallback : DiffUtil.ItemCallback<UnsplashPhoto>(){
    override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
        return oldItem == newItem
    }
}

class PicturePickerListener(val clickListener: (pictureId: String) -> Unit){
    fun onClick(unsplashPhoto: UnsplashPhoto) = clickListener(unsplashPhoto.id)
}