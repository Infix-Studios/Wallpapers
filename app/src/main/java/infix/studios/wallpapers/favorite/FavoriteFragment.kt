package infix.studios.wallpapers.favorite

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import infix.studios.wallpapers.R
import infix.studios.wallpapers.databinding.FavoriteFragmentBinding
import infix.studios.wallpapers.di.ViewModelProviderFactory
import infix.studios.wallpapers.model.FavoritePhoto
import infix.studios.wallpapers.util.ClickListenerSearch
import infix.studios.wallpapers.util.Resource
import infix.studios.wallpapers.util.isNetworkAvailable
import kotlinx.android.synthetic.main.empty.view.*
import timber.log.Timber
import javax.inject.Inject

class FavoriteFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var binding: FavoriteFragmentBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: FavoriteAdapter

    private val observer = Observer<Resource<List<FavoritePhoto>>> {
        when (it.status) {
            Resource.Status.SUCCESS -> updateAdapter(it.data)
            Resource.Status.ERROR -> showError(it.message!!)
            Resource.Status.LOADING -> showLoading()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.favorite_fragment, container, false)

        viewModel = ViewModelProvider(this, factory).get(FavoriteViewModel::class.java)

        adapter = FavoriteAdapter(ClickListenerSearch {
            this.findNavController().navigate(FavoriteFragmentDirections
                .actionFavoriteFragmentToFavoriteDetailsFragment(it))
        })

        binding.recyclerView.adapter = adapter

        viewModel.getFavoritePhotos.observe(viewLifecycleOwner, observer)

        return binding.root
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

    private fun updateAdapter(listOfFavoritePhotos: List<FavoritePhoto>?) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        adapter.submitList(listOfFavoritePhotos)

        if (!isNetworkAvailable(context)){
            showError("No internet")
        }
    }
}