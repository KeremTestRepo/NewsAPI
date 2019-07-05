package com.necatisozer.newsapi.extension

import android.widget.ImageView
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.necatisozer.newsapi.R

private const val placeholder = R.drawable.sample_image

fun ImageView.loadUrlAsCircle(url: String) {
    Glide.with(this)
        .load(url)
        .apply(RequestOptions.circleCropTransform())
        .placeholder(placeholder)
        .into(this)
}

fun ImageView.loadUrl(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(placeholder)
        .into(this)
}

fun ImageView.loadDrawableRes(@DrawableRes drawableRes: Int) {
    Glide.with(this)
        .load(drawableRes)
        .placeholder(placeholder)
        .into(this)
}

fun ImageView.loadUrl(url: String, @DimenRes roundingRadius: Int) {
    Glide.with(this)
        .load(url)
        .apply(
            RequestOptions().transform(
                CenterInside(),
                RoundedCorners(context.resources.getDimensionPixelSize(roundingRadius))
            )
        )
        .placeholder(placeholder)
        .into(this)
}