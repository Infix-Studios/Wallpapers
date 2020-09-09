package infix.studios.wallpapers.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import infix.studios.wallpapers.databinding.PhotoSearchItemBinding
import infix.studios.wallpapers.model.FavoritePhoto
import infix.studios.wallpapers.util.ClickListenerSearch
import infix.studios.wallpapers.util.DataDiffCallbackFavorite

class FavoriteAdapter(private val clickListener: ClickListenerSearch) :
    ListAdapter<FavoritePhoto, RecyclerView.ViewHolder>(DataDiffCallbackFavorite()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FavoriteViewHolder(
            PhotoSearchItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as FavoriteViewHolder).bind(clickListener, data.url)
    }

    class FavoriteViewHolder(private val binding: PhotoSearchItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: ClickListenerSearch, item: String) {
            binding.apply {
                clickListener = listener
                result = item
                executePendingBindings()
            }
        }
    }
}