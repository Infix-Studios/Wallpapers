package infix.studios.wallpapers.di.categorieslist

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import infix.studios.wallpapers.categories.categorylist.CategoryListViewModel
import infix.studios.wallpapers.di.ViewModelKey

@Module
abstract class CategoryListViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CategoryListViewModel::class)
    abstract fun bindCategoryListViewModel(categoryListViewModel: CategoryListViewModel): ViewModel
}