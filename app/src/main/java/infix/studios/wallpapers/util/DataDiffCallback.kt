package infix.studios.wallpapers.util

import androidx.recyclerview.widget.DiffUtil
import infix.studios.wallpapers.model.Photo
import infix.studios.wallpapers.model.PhotoSearch

class DataDiffCallback: DiffUtil.ItemCallback<Photo.PhotoItem>() {
    override fun areItemsTheSame(oldItem: Photo.PhotoItem, newItem: Photo.PhotoItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Photo.PhotoItem, newItem: Photo.PhotoItem): Boolean {
        return oldItem == newItem
    }
}

class DataDiffCallbackSearch: DiffUtil.ItemCallback<PhotoSearch.Result>() {
    override fun areItemsTheSame(oldItem: PhotoSearch.Result, newItem: PhotoSearch.Result): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PhotoSearch.Result, newItem: PhotoSearch.Result): Boolean {
        return oldItem == newItem
    }
}