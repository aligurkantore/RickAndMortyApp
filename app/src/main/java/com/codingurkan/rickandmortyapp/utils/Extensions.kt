package com.codingurkan.rickandmortyapp.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}
fun showToast(context : Context){
    Toast.makeText(context, "You entered incomplete information", Toast.LENGTH_SHORT).show()
}