package infix.studios.wallpapers.feeddetails

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import infix.studios.wallpapers.R
import infix.studios.wallpapers.databinding.FragmentFeedDetailsBinding
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class FeedDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentFeedDetailsBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_feed_details, container, false)

        val args = FeedDetailsFragmentArgs.fromBundle(requireArguments())

        binding.photoItem = args.photoItem

        Timber.i("\n\n**************details")

        val actionBar: android.app.ActionBar? = requireActivity().actionBar

        actionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#80000000")))

        return binding.root
    }

}
