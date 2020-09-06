package infix.studios.wallpapers.di.categorylistdetails

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import infix.studios.wallpapers.categories.categorylistdetails.CategoryListDetailsViewModel
import infix.studios.wallpapers.di.ViewModelKey

@Module
abstract class CategoryListDetailsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CategoryListDetailsViewModel::class)
    abstract fun bindCategoryListDetailsViewModel(categoryListDetailsViewModel: CategoryListDetailsViewModel): ViewModel
}