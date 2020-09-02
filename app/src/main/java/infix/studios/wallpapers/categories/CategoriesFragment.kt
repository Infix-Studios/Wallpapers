package infix.studios.wallpapers.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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

        return binding.root
    }

}