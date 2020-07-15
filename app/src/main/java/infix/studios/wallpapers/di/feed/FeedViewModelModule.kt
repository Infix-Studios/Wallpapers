package infix.studios.wallpapers.di.feed

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import infix.studios.wallpapers.di.ViewModelKey
import infix.studios.wallpapers.feed.FeedViewModel

@Module
abstract class FeedViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    abstract fun bindFeedViewModel(feedViewModel: FeedViewModel): ViewModel
}