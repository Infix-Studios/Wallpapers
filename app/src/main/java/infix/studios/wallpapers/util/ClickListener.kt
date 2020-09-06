package infix.studios.wallpapers.util

import infix.studios.wallpapers.model.Photo
import infix.studios.wallpapers.model.PhotoSearch

class ClickListener(val clickListener: (photoItem: Photo.PhotoItem) -> Unit) {
    fun onClick(photoItem: Photo.PhotoItem) = clickListener(photoItem)
}

class ClickListenerSearch(val clickListener: (result: PhotoSearch.Result) -> Unit) {
    fun onClick(result: PhotoSearch.Result) = clickListener(result)
}