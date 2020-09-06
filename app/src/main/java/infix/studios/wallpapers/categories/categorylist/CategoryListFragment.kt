package infix.studios.wallpapers.categories.categorylist

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.android.support.DaggerFragment
import infix.studios.wallpapers.R
import infix.studios.wallpapers.categories.categorylistdetails.CategoryListDetailsFragmentArgs
import infix.studios.wallpapers.databinding.CategoryListFragmentBinding
import infix.studios.wallpapers.di.ViewModelProviderFactory
import infix.studios.wallpapers.model.PhotoSearch
import infix.studios.wallpapers.util.ClickListenerSearch
import infix.studios.wallpapers.util.Resource
import infix.studios.wallpapers.util.isNetworkAvailable
import kotlinx.android.synthetic.main.empty.view.*
import timber.log.Timber
import javax.inject.Inject

class CategoryListFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var binding: CategoryListFragmentBinding
    private lateinit var viewModel: CategoryListViewModel
    private lateinit var adapter: CategoryListAdapter
    private val args: CategoryListFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.category_list_fragment, container, false)

        viewModel = ViewModelProvider(this, factory).get(CategoryListViewModel::class.java)

        adapter = CategoryListAdapter(ClickListenerSearch {
            this.findNavController().navigate(CategoryListFragmentDirections
                    .actionCategoryListFragmentToCategoryListDetailsFragment(it))
        })

        binding.recyclerView.adapter = adapter

        searchWallpapers(args.category)

        return binding.root
    }

    private fun searchWallpapers(query: String) {
        viewModel.searchPhotos(query).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> updateAdapter(it.data?.results)
                Resource.Status.ERROR -> showError(it.message!!)
                Resource.Status.LOADING -> showLoading()
            }
        })
    }

    private fun showLoading() {
        // Show Loading bar
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }

    private fun showError(message: String) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE

        // Show new dialog with the error
        Timber.d("Error: $message")
        val dialogView = LayoutInflater.from(context).inflate(R.layout.empty, null)
        val builder = AlertDialog.Builder(context)
                .setView(dialogView)
        val alertDialog = builder.show()
        dialogView.retry_button.setOnClickListener {
            alertDialog.dismiss()
        }
    }

    private fun updateAdapter(photo: List<PhotoSearch.Result>?) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        adapter.submitList(photo)

        Timber.i("\n\n*****SearchFragment: ${photo?.get(0)?.urls?.small}")
        if (!isNetworkAvailable(context)){
            showError("No internet")
        }
    }

}