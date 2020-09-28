package infix.studios.wallpapers.home.homedetails

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import dagger.android.support.DaggerFragment
import infix.studios.wallpapers.MainActivity
import infix.studios.wallpapers.R
import infix.studios.wallpapers.databinding.HomeDetailsFragmentBinding
import infix.studios.wallpapers.di.ViewModelProviderFactory
import infix.studios.wallpapers.model.FavoritePhoto
import infix.studios.wallpapers.util.getLocalBitmapUri
import infix.studios.wallpapers.util.setWallpaperDialog
import timber.log.Timber
import javax.inject.Inject

class HomeDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: HomeDetailsViewModel
    private lateinit var binding: HomeDetailsFragmentBinding
    private val args: HomeDetailsFragmentArgs by navArgs()
    private lateinit var currentMenuIcon: MenuItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_details_fragment, container, false)

        viewModel = ViewModelProvider(this, factory).get(HomeDetailsViewModel::class.java)

        viewModel.photo.observe(viewLifecycleOwner, Observer {
            binding.photoItem =  it
        })

        viewModel.setPhoto(args.url)

        binding.setWallpaperButton.setOnClickListener { setWallpaperDialog(requireContext(), args.url) }

        binding.shareButton.setOnClickListener {
            val bmpUri = getLocalBitmapUri(binding.searchDetailsImageView, requireContext())
            if (bmpUri != null) {
                // Construct a ShareIntent with link to image
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri)
                shareIntent.type = "image/*"
                shareIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                // Launch sharing dialog for image
                val chooser = Intent.createChooser(shareIntent, "Share Image")
                val resInfoList: List<ResolveInfo> = requireActivity().packageManager
                    .queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY)
                for (resolveInfo in resInfoList) {
                    val packageName = resolveInfo.activityInfo.packageName
                    requireActivity().grantUriPermission(packageName, bmpUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                startActivity(chooser)
            } else {
                // ...sharing failed, handle error
                Timber.d("Sharing failed")
                Toast.makeText(context, "Failed to share", Toast.LENGTH_SHORT).show()
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_favorite_menu, menu)
        currentMenuIcon = menu.findItem(R.id.favorite_menu_icon)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.favorite_menu_icon -> {
                if (currentMenuIcon.icon.constantState?.equals(
                        ContextCompat.getDrawable(requireContext(),
                            R.drawable.ic_unliked_favorite_24)?.constantState)!!) {

                    Toast.makeText(context, "Added to favorite", Toast.LENGTH_SHORT).show()
                    viewModel.saveFavoritePhoto(FavoritePhoto(url = args.url))
                    currentMenuIcon.icon = AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.ic_white_favorite_24
                    )
                } else {
                    //Toast.makeText(context, "Removed to favorite", Toast.LENGTH_SHORT).show()
                    currentMenuIcon.icon = AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.ic_unliked_favorite_24
                    )
                }
            }
        }
        return super.onOptionsItemSelected(item)
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