package infix.studios.wallpapers.categories.categorylistdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import dagger.android.support.DaggerFragment
import infix.studios.wallpapers.MainActivity
import infix.studios.wallpapers.R
import infix.studios.wallpapers.databinding.CategoryListDetailsFragmentBinding
import infix.studios.wallpapers.di.ViewModelProviderFactory
import infix.studios.wallpapers.search.searchdetails.SearchDetailsFragmentArgs
import javax.inject.Inject

class CategoryListDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var binding: CategoryListDetailsFragmentBinding
    private lateinit var viewModel: CategoryListDetailsViewModel
    private val args: CategoryListDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.category_list_details_fragment, container, false)

        viewModel = ViewModelProvider(this, factory).get(CategoryListDetailsViewModel::class.java)

        viewModel.setPhoto(args.result)

        viewModel.photo.observe(viewLifecycleOwner, Observer {
            binding.result =  it
        })

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).hideBottomNavigation()
    }

    override fun onDetach() {
        (activity as MainActivity).showBottomNavigation()
        super.onDetach()
    }


}