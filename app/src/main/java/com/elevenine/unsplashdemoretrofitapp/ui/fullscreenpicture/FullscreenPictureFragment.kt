package com.elevenine.unsplashdemoretrofitapp.ui.fullscreenpicture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.elevenine.unsplashdemoretrofitapp.R
import com.elevenine.unsplashdemoretrofitapp.data.db.UnsplashDatabase
import com.elevenine.unsplashdemoretrofitapp.data.network.UnsplashApiFactory
import com.elevenine.unsplashdemoretrofitapp.data.network.UnsplashNetworkDataSource
import com.elevenine.unsplashdemoretrofitapp.databinding.FullscreenPictureBinding
import com.elevenine.unsplashdemoretrofitapp.repository.UnsplashRepository
import com.squareup.picasso.Picasso

class FullscreenPictureFragment : Fragment(){

    private lateinit var fullscreenPictureViewModel: FullscreenPictureViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FullscreenPictureBinding = DataBindingUtil.inflate(
            inflater, R.layout.fullscreen_picture, container, false
        )
        val application = requireNotNull(this.activity).application
        val dao = UnsplashDatabase.getInstance(application).unsplashDao
        val networkDataSource = UnsplashNetworkDataSource(UnsplashApiFactory.unsplashApi)
        val repository = UnsplashRepository(networkDataSource, dao)

        // Fetch arguments from the fragments transition.
        val args = FullscreenPictureFragmentArgs.fromBundle(requireArguments())

        val viewModelFactory
                = FullscreenPictureViewModelFactory(repository, args.photoId)

        fullscreenPictureViewModel = ViewModelProvider(this, viewModelFactory)
            .get(FullscreenPictureViewModel::class.java)

        fullscreenPictureViewModel.unsplashPhotoLiveData.observe(viewLifecycleOwner, Observer {
            photo ->
                Picasso.get().load(photo.urls.full)
                .into(binding.pictureImageView)
        })

        return binding.root
    }
}