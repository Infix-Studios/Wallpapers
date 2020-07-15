package infix.studios.wallpapers.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import infix.studios.wallpapers.databinding.PhotoListItemBinding
import infix.studios.wallpapers.model.Photo

class FeedAdapter : ListAdapter<Photo.PhotoItem, RecyclerView.ViewHolder>(DataDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FeedViewHolder(PhotoListItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as FeedViewHolder).bind(data)
    }

    class FeedViewHolder(private val binding: PhotoListItemBinding):
            RecyclerView.ViewHolder(binding.root){

        fun bind(item: Photo.PhotoItem){
            binding.apply {
                photoItem = item
                executePendingBindings()
            }
        }
    }
}

private class DataDiffCallback: DiffUtil.ItemCallback<Photo.PhotoItem>(){
    override fun areItemsTheSame(oldItem: Photo.PhotoItem, newItem: Photo.PhotoItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Photo.PhotoItem, newItem: Photo.PhotoItem): Boolean {
        return oldItem == newItem
    }
}