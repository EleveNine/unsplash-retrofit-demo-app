package com.elevenine.unsplashdemoretrofitapp.ui.picturepicker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.elevenine.unsplashdemoretrofitapp.R
import com.elevenine.unsplashdemoretrofitapp.data.db.UnsplashDatabase
import com.elevenine.unsplashdemoretrofitapp.data.network.UnsplashApiFactory
import com.elevenine.unsplashdemoretrofitapp.data.network.UnsplashNetworkDataSource
import com.elevenine.unsplashdemoretrofitapp.databinding.FragmentPicturePickerBinding
import com.elevenine.unsplashdemoretrofitapp.repository.UnsplashRepository

class PicturePickerFragment : Fragment() {

    private lateinit var picturePickerViewModel: PicturePickerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentPicturePickerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_picture_picker, container, false)

        val application = requireNotNull(this.activity).application
        val dao = UnsplashDatabase.getInstance(application).unsplashDao
        val networkDataSource = UnsplashNetworkDataSource(UnsplashApiFactory.unsplashApi)
        val repository = UnsplashRepository(networkDataSource, dao)

        val picturePickerViewModelFactory =
            PicturePickerViewModelFactory(repository, application)

        picturePickerViewModel = ViewModelProvider(this, picturePickerViewModelFactory)
                .get(PicturePickerViewModel::class.java)

        val picturePickerAdapter = PicturePickerAdapter(PicturePickerListener {
                photoId -> picturePickerViewModel.onPictureClicked(photoId)
        })

        binding.picturePickerRecyclerView.adapter = picturePickerAdapter
        binding.picturePickerRecyclerView.layoutManager = GridLayoutManager(this.context, 2)

        picturePickerViewModel.photosLiveData.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                for (url in it.indices)
                    Log.d("MainActivity: ${it.size}, $url", it[url].urls.full)

                picturePickerAdapter.submitList(it)
            }
        })

        picturePickerViewModel.navigateToFullscreenPicture.observe(viewLifecycleOwner, Observer {
            it?.let{
                Log.d("PicturePickerFragment", "navigation triggered")
                this.findNavController().navigate(
                    PicturePickerFragmentDirections
                        .actionPicturePickerFragmentToFullscreenPictureFragment(it)
                )
            }
            picturePickerViewModel.onFullscreenPictureNavigated()
        })

        // Inflate the layout for this fragment
        return binding.root
    }
}