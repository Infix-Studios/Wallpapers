package infix.studios.wallpapers.search.searchdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import dagger.android.support.DaggerFragment
import infix.studios.wallpapers.MainActivity
import infix.studios.wallpapers.R
import infix.studios.wallpapers.databinding.SearchDetailsFragmentBinding
import infix.studios.wallpapers.di.ViewModelProviderFactory
import infix.studios.wallpapers.util.getLocalBitmapUri
import infix.studios.wallpapers.util.setWallpaperDialog
import timber.log.Timber
import javax.inject.Inject

class SearchDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory
    private lateinit var viewModel: SearchDetailsViewModel
    private lateinit var binding: SearchDetailsFragmentBinding
    private val args: SearchDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_details_fragment, container, false)

        viewModel = ViewModelProvider(this, factory).get(SearchDetailsViewModel::class.java)

        viewModel.setPhoto(args.url)

        viewModel.photo.observe(viewLifecycleOwner, Observer {
            binding.result =  it
        })
        binding.setWallpaperButton.setOnClickListener { setWallpaperDialog(requireContext(), args.url) }

        binding.shareButton.setOnClickListener {
            val bmpUri = getLocalBitmapUri(binding.searchDetailsImageView, requireContext())
            if (bmpUri != null) {
                // Construct a ShareIntent with link to image
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri)
                shareIntent.type = "image/*"
                // Launch sharing dialog for image
                startActivity(Intent.createChooser(shareIntent, "Share Image"))
            } else {
                // ...sharing failed, handle error
                Timber.d("Sharing failed")
                Toast.makeText(context, "Failed to share", Toast.LENGTH_SHORT).show()
            }
        }

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