package infix.studios.wallpapers.feedDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

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

//        val args = FeedDetailsFragmentArgs.fromBundle(requireArguments())
//
//        binding.photoItem = args.photoItem

        Timber.i("\n\n**************details")

        return binding.root
    }

}
