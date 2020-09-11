package infix.studios.wallpapers.search

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import infix.studios.wallpapers.R
import infix.studios.wallpapers.databinding.SearchFragmentBinding
import infix.studios.wallpapers.di.ViewModelProviderFactory
import infix.studios.wallpapers.model.PhotoSearch
import infix.studios.wallpapers.util.*
import kotlinx.android.synthetic.main.empty.view.*
import timber.log.Timber
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var binding: SearchFragmentBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)

        binding.lifecycleOwner = this.viewLifecycleOwner

        viewModel = ViewModelProvider(this, factory).get(SearchViewModel::class.java)


        adapter = SearchAdapter(ClickListenerSearch {
            this.findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToSearchDetailsFragment(it))
        })

        binding.recyclerView.adapter = adapter

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val search = menu.findItem(R.id.search_bar)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search wallpapers..."

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (p0 != null) {
                    searchWallpapers(p0)
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })

        searchView.setOnCloseListener {
            // Hides keyboard
            view?.hideKeyboard()

            // Removes focus from searchView
            searchView.clearFocus()

            // After clicking cross (x), searchView bar collapses & goes back to original position
            searchView.onActionViewCollapsed()
            return@setOnCloseListener true
        }
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