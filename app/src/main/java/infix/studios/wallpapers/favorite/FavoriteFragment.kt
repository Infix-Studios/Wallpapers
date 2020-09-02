package infix.studios.wallpapers.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import infix.studios.wallpapers.R
import infix.studios.wallpapers.databinding.FavoriteFragmentBinding
import infix.studios.wallpapers.di.ViewModelProviderFactory
import javax.inject.Inject

class FavoriteFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var binding: FavoriteFragmentBinding
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.favorite_fragment, container, false)

        viewModel = ViewModelProvider(this, factory).get(FavoriteViewModel::class.java)

        return binding.root
    }

}