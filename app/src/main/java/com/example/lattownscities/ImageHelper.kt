package com.example.lattownscities


import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import java.util.*

fun ImageView.loadImg(url : String) {
    if (url.toLowerCase(Locale.getDefault()).endsWith(".svg")){
        GlideToVectorYou
            .init()
            .with(context)
            .load(url.toUri(), this)
    } else {
        Glide.with(context)
            .load(url)
            .into(this)
    }
}
