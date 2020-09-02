package infix.studios.wallpapers.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import infix.studios.wallpapers.R
import infix.studios.wallpapers.databinding.HomeFragmentBinding
import infix.studios.wallpapers.di.ViewModelProviderFactory
import infix.studios.wallpapers.model.Photo
import infix.studios.wallpapers.util.ClickListener
import infix.studios.wallpapers.util.Resource
import infix.studios.wallpapers.util.isNetworkAvailable
import kotlinx.android.synthetic.main.empty.view.*
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding
    private lateinit var adapter: HomeAdapter


    private val observer = Observer<Resource<Photo>> {
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

        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)

        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        adapter = HomeAdapter(ClickListener {

        })

        binding.recyclerView.adapter = adapter

        viewModel.getPhotos.observe(viewLifecycleOwner, observer)

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

    private fun updateAdapter(photo: Photo?) {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        adapter.submitList(photo)

        Timber.i("\n\n**************fragmnet: ${photo}")
        if (!isNetworkAvailable(context)){
            showError("No internet")
        }
    }

}