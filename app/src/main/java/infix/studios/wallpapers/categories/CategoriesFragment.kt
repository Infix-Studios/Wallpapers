package infix.studios.wallpapers.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import infix.studios.wallpapers.R
import infix.studios.wallpapers.databinding.CategoriesFragmentBinding
import infix.studios.wallpapers.di.ViewModelProviderFactory
import javax.inject.Inject

class CategoriesFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var binding: CategoriesFragmentBinding
    private lateinit var viewModel: CategoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.categories_fragment, container, false)

        viewModel = ViewModelProvider(this, factory).get(CategoriesViewModel::class.java)

        binding.animalsCategoryButton.setOnClickListener { moveToCategoryList("animal") }

        binding.architectureCategoryButton.setOnClickListener { moveToCategoryList("architecture") }

        binding.artCategoryButton.setOnClickListener { moveToCategoryList("art") }

        binding.carsCategoryButton.setOnClickListener { moveToCategoryList("cars") }

        binding.skyCategoryButton.setOnClickListener { moveToCategoryList("sky") }

        binding.natureCategoryButton.setOnClickListener { moveToCategoryList("nature") }

        return binding.root
    }

    private fun moveToCategoryList(category: String) {
        this.findNavController().navigate(CategoriesFragmentDirections
                .actionCategoriesFragmentToCategoryListFragment(category))
    }
}