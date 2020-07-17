package infix.studios.wallpapers.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import infix.studios.wallpapers.databinding.PhotoListItemBinding
import infix.studios.wallpapers.model.Photo

class FeedAdapter(private val clickListener: ClickListener) :
    ListAdapter<Photo.PhotoItem, RecyclerView.ViewHolder>(DataDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FeedViewHolder(PhotoListItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as FeedViewHolder).bind(clickListener, data)
    }

    class FeedViewHolder(private val binding: PhotoListItemBinding):
            RecyclerView.ViewHolder(binding.root){

        fun bind(listener: ClickListener, item: Photo.PhotoItem){
            binding.apply {
                clickListener = listener
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

class ClickListener(val clickListener: (photoItem: Photo.PhotoItem) -> Unit) {
    fun onClick(photoItem: Photo.PhotoItem) = clickListener(photoItem)
}


//package infix.studios.wallpapers.feed
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import infix.studios.wallpapers.databinding.PhotoListItemBinding
//import infix.studios.wallpapers.model.Photo
//
//class FeedAdapter(private val clickListener: ClickListener) :
//    ListAdapter<Photo, RecyclerView.ViewHolder>(DataDiffCallback()){
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return FeedViewHolder(PhotoListItemBinding.inflate(LayoutInflater.from(parent.context),
//            parent, false))
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val data = getItem(position)
//        (holder as FeedViewHolder).bind(clickListener, data)
//    }
//
//    class FeedViewHolder(private val binding: PhotoListItemBinding):
//        RecyclerView.ViewHolder(binding.root){
//
//        fun bind(listener: ClickListener, item: Photo){
//            binding.apply {
//                clickListener = listener
//                photo = item
//                executePendingBindings()
//            }
//        }
//    }
//}
//
//private class DataDiffCallback: DiffUtil.ItemCallback<Photo>(){
//    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
//        return oldItem == newItem
//    }
//
//    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
//        return oldItem == newItem
//    }
//}
//
//class ClickListener(val clickListener: (photo: Photo) -> Unit) {
//    fun onClick(photo: Photo) = clickListener(photo)
//}




//package infix.studios.wallpapers.feed
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import infix.studios.wallpapers.databinding.PhotoListItemBinding
//import infix.studios.wallpapers.model.Photo
//
//class FeedAdapter : ListAdapter<Photo.PhotoItem, RecyclerView.ViewHolder>(DataDiffCallback()){
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return FeedViewHolder(PhotoListItemBinding.inflate(LayoutInflater.from(parent.context),
//            parent, false))
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val data = getItem(position)
//        (holder as FeedViewHolder).bind(data)
//    }
//
//    class FeedViewHolder(private val binding: PhotoListItemBinding):
//            RecyclerView.ViewHolder(binding.root){
//
//        fun bind(item: Photo.PhotoItem){
//            binding.apply {
//                photoItem = item
//                executePendingBindings()
//            }
//        }
//    }
//}
//
//private class DataDiffCallback: DiffUtil.ItemCallback<Photo.PhotoItem>(){
//    override fun areItemsTheSame(oldItem: Photo.PhotoItem, newItem: Photo.PhotoItem): Boolean {
//        return oldItem == newItem
//    }
//
//    override fun areContentsTheSame(oldItem: Photo.PhotoItem, newItem: Photo.PhotoItem): Boolean {
//        return oldItem == newItem
//    }
//}
//
//class ClickListener(val clickListener: (photoItem: Photo.PhotoItem) -> Unit) {
//    fun onClick(photoItem: Photo.PhotoItem) = clickListener(photoItem)
//}