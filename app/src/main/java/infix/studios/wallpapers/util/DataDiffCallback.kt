package infix.studios.wallpapers.util

import androidx.recyclerview.widget.DiffUtil
import infix.studios.wallpapers.model.Photo

class DataDiffCallback: DiffUtil.ItemCallback<Photo.PhotoItem>() {
    override fun areItemsTheSame(oldItem: Photo.PhotoItem, newItem: Photo.PhotoItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Photo.PhotoItem, newItem: Photo.PhotoItem): Boolean {
        return oldItem == newItem
    }
}