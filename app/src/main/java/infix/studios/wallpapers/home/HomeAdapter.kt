package infix.studios.wallpapers.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import infix.studios.wallpapers.databinding.PhotoListItemBinding
import infix.studios.wallpapers.model.Photo
import infix.studios.wallpapers.util.ClickListener
import infix.studios.wallpapers.util.DataDiffCallback

class HomeAdapter(private val clickListener: ClickListener) :
    ListAdapter<Photo.PhotoItem, RecyclerView.ViewHolder>(DataDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeViewHolder(PhotoListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as HomeViewHolder).bind(clickListener, data)
    }

    class HomeViewHolder(private val binding: PhotoListItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: ClickListener, item: Photo.PhotoItem){
            binding.apply {
                clickListener = listener
                photoItem = item
                executePendingBindings()
            }
        }
    }
}