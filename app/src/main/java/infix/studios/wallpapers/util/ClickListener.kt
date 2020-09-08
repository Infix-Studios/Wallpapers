package infix.studios.wallpapers.util

import infix.studios.wallpapers.model.PhotoItem

class ClickListener(val clickListener: (photoItem: PhotoItem) -> Unit) {
    fun onClick(photoItem: PhotoItem) = clickListener(photoItem)
}

class ClickListenerSearch(val clickListener: (result: String) -> Unit) {
    fun onClick(result: String) = clickListener(result)
}