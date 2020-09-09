package infix.studios.wallpapers.categories.categorylistdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import dagger.android.support.DaggerFragment
import infix.studios.wallpapers.MainActivity
import infix.studios.wallpapers.R
import infix.studios.wallpapers.databinding.CategoryListDetailsFragmentBinding
import infix.studios.wallpapers.di.ViewModelProviderFactory
import infix.studios.wallpapers.model.FavoritePhoto
import infix.studios.wallpapers.util.getLocalBitmapUri
import infix.studios.wallpapers.util.setWallpaperDialog
import timber.log.Timber
import javax.inject.Inject


class CategoryListDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var binding: CategoryListDetailsFragmentBinding
    private lateinit var viewModel: CategoryListDetailsViewModel
    private val args: CategoryListDetailsFragmentArgs by navArgs()
    private lateinit var currentMenuIcon: MenuItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.category_list_details_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this, factory).get(CategoryListDetailsViewModel::class.java)

        viewModel.setPhoto(args.url)

        viewModel.photo.observe(viewLifecycleOwner, {
            binding.result = it
        })

        binding.setWallpaperButton.setOnClickListener { setWallpaperDialog(
            requireContext(),
            args.url
        ) }

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