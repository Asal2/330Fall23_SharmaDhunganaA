// Import necessary packages and classes
package com.example.dictionaryapp

import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load

// Define a BindingAdapter for loading images into ImageView using Coil library
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    // Log a message indicating that the binding adapter is called with the provided URL


    // Use Kotlin's safe call operator (?.) to safely handle null values of imgUrl
    imgUrl?.let {
        // Convert the imgUrl String to a Uri, and append a base URL for image retrieval
        val imgUri = imgUrl.toUri().buildUpon().scheme("https://www.merriam-webster.com/assets/mw/static/art/dict/").build()

        // Use Coil library to load the image into the ImageView
        imgView.load(imgUri) {
            // Set placeholder and error images in case of loading or error situations
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_foreground)
        }
    }
}
