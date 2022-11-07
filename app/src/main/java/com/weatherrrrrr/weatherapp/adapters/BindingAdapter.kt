package com.weatherrrrrr.weatherapp.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun bindPictureOfDayImage(image: ImageView, url: String?) {
    if(url!=null)
        Picasso.with(image.context).load("https://openweathermap.org/img/w/$url.png").into(image)
}