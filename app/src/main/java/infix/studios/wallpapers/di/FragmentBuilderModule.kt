package infix.studios.wallpapers.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import infix.studios.wallpapers.di.feed.FeedViewModelModule
import infix.studios.wallpapers.feed.FeedFragment

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [FeedViewModelModule::class])
    abstract fun contributeFeedFragment() : FeedFragment
}