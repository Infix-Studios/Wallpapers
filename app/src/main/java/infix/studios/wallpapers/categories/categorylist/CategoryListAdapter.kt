package infix.studios.wallpapers.categories.categorylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import infix.studios.wallpapers.databinding.PhotoSearchItemBinding
import infix.studios.wallpapers.model.PhotoSearch
import infix.studios.wallpapers.util.ClickListenerSearch
import infix.studios.wallpapers.util.DataDiffCallbackSearch

class CategoryListAdapter(private val clickListener: ClickListenerSearch) :
        ListAdapter<PhotoSearch.Result, RecyclerView.ViewHolder>(DataDiffCallbackSearch()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoryListViewHolder(PhotoSearchItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as CategoryListViewHolder).bind(clickListener, data.urls.regular)
    }


    class CategoryListViewHolder(private val binding: PhotoSearchItemBinding):
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