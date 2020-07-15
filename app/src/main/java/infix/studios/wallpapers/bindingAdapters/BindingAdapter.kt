package infix.studios.wallpapers.bindingAdapters

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import infix.studios.wallpapers.R

@BindingAdapter("photoUrl")
fun bindImage(imageView: ImageView, photoUrl: String?){

    photoUrl?.let {
        val photoUri = photoUrl.toUri()

        Glide.with(imageView.context)
            .load(photoUri)
            .placeholder(R.drawable.loading_animation)
            .centerCrop()
            .error(R.drawable.ic_loading_photo)
            .into(imageView)
    }
}