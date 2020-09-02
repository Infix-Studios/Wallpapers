package infix.studios.wallpapers.util

import infix.studios.wallpapers.model.Photo

class ClickListener(val clickListener: (photoItem: Photo.PhotoItem) -> Unit) {
    fun onClick(photoItem: Photo.PhotoItem) = clickListener(photoItem)
}