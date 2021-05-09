package com.interview.winvesta.ui.movie

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.interview.winvesta.BuildConfig
import com.interview.winvesta.data.api.MovieService
import com.interview.winvesta.utils.IMAGE_BASE_URL
import com.interview.winvesta.utils.IMAGE_FILE_SIZE


@BindingAdapter("imageFromUrl")
    fun bindImageFromUrl(view: ImageView, imageUrl: String?) {

     val image = IMAGE_BASE_URL + IMAGE_FILE_SIZE + imageUrl

    if (!imageUrl.isNullOrEmpty()) {
            Glide.with(view.context)
                .load(image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        }
    }
