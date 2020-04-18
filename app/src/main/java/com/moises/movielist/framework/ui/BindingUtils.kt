package com.moises.movielist.framework.ui


import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.moises.movielist.core.arch.Constants.Companion.IMAGE_URL
import com.squareup.picasso.Picasso


object UiUtil {
    @BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = true)
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String?,  placeholder: Drawable) =
        Picasso.get()
                .load(IMAGE_URL + imageUrl)
                .error(placeholder)
                .into(view)


}