package infix.studios.wallpapers.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import infix.studios.wallpapers.categories.CategoriesFragment
import infix.studios.wallpapers.di.categories.CategoriesViewModelModule
import infix.studios.wallpapers.di.favorite.FavoriteViewModelModule
import infix.studios.wallpapers.di.feed.FeedViewModelModule
import infix.studios.wallpapers.di.home.HomeViewModelModule
import infix.studios.wallpapers.di.search.SearchViewModelModule
import infix.studios.wallpapers.favorite.FavoriteFragment
import infix.studios.wallpapers.feed.FeedFragment
import infix.studios.wallpapers.home.HomeFragment
import infix.studios.wallpapers.search.SearchFragment

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [FeedViewModelModule::class])
    abstract fun contributeFeedFragment() : FeedFragment

    @ContributesAndroidInjector(modules = [FavoriteViewModelModule::class])
    abstract fun contributeFavoriteFragment() : FavoriteFragment

    @ContributesAndroidInjector(modules = [HomeViewModelModule::class])
    abstract fun contributeHomeFragment() : HomeFragment

    @ContributesAndroidInjector(modules = [CategoriesViewModelModule::class])
    abstract fun contributeCategoriesFragment() : CategoriesFragment

    @ContributesAndroidInjector(modules = [SearchViewModelModule::class])
    abstract fun contributeSearchFragment() : SearchFragment
}