package infix.studios.wallpapers.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import infix.studios.wallpapers.categories.CategoriesFragment
import infix.studios.wallpapers.categories.categorylist.CategoryListFragment
import infix.studios.wallpapers.categories.categorylistdetails.CategoryListDetailsFragment
import infix.studios.wallpapers.di.categories.CategoriesViewModelModule
import infix.studios.wallpapers.di.categorieslist.CategoryListViewModelModule
import infix.studios.wallpapers.di.categorylistdetails.CategoryListDetailsViewModelModule
import infix.studios.wallpapers.di.favorite.FavoriteViewModelModule
import infix.studios.wallpapers.di.home.HomeViewModelModule
import infix.studios.wallpapers.di.homedetails.HomeDetailsViewModelModule
import infix.studios.wallpapers.di.search.SearchViewModelModule
import infix.studios.wallpapers.di.searchdetails.SearchDetailsViewModelModule
import infix.studios.wallpapers.favorite.FavoriteFragment
import infix.studios.wallpapers.favorite.favoritedetails.FavoriteDetailsFragment
import infix.studios.wallpapers.home.HomeFragment
import infix.studios.wallpapers.home.homedetails.HomeDetailsFragment
import infix.studios.wallpapers.search.SearchFragment
import infix.studios.wallpapers.search.searchdetails.SearchDetailsFragment

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [HomeDetailsViewModelModule::class])
    abstract fun contributeHomeDetailsFragment() : HomeDetailsFragment

    @ContributesAndroidInjector(modules = [FavoriteViewModelModule::class])
    abstract fun contributeFavoriteFragment() : FavoriteFragment

    @ContributesAndroidInjector(modules = [FavoriteDetailsViewModelModule::class])
    abstract fun contributeFavoriteDetailsFragment() : FavoriteDetailsFragment

    @ContributesAndroidInjector(modules = [HomeViewModelModule::class])
    abstract fun contributeHomeFragment() : HomeFragment

    @ContributesAndroidInjector(modules = [CategoriesViewModelModule::class])
    abstract fun contributeCategoriesFragment() : CategoriesFragment

    @ContributesAndroidInjector(modules = [SearchViewModelModule::class])
    abstract fun contributeSearchFragment() : SearchFragment

    @ContributesAndroidInjector(modules = [SearchDetailsViewModelModule::class])
    abstract fun contributeSearchDetailsFragment() : SearchDetailsFragment

    @ContributesAndroidInjector(modules = [CategoryListViewModelModule::class])
    abstract fun contributeCategoryListFragment() : CategoryListFragment

    @ContributesAndroidInjector(modules = [CategoryListDetailsViewModelModule::class])
    abstract fun contributeCategoryListDetailsFragment() : CategoryListDetailsFragment

}