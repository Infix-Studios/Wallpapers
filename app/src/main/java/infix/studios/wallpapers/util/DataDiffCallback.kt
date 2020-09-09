package infix.studios.wallpapers.util

import androidx.recyclerview.widget.DiffUtil
import infix.studios.wallpapers.model.FavoritePhoto
import infix.studios.wallpapers.model.PhotoItem
import infix.studios.wallpapers.model.PhotoSearch

class DataDiffCallback: DiffUtil.ItemCallback<PhotoItem>() {
    override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
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

class DataDiffCallbackFavorite: DiffUtil.ItemCallback<FavoritePhoto>() {
    override fun areItemsTheSame(oldItem: FavoritePhoto, newItem: FavoritePhoto): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: FavoritePhoto, newItem: FavoritePhoto): Boolean {
        return oldItem == newItem
    }
}