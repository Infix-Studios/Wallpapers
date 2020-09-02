package infix.studios.wallpapers.di.categories

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import infix.studios.wallpapers.categories.CategoriesViewModel
import infix.studios.wallpapers.di.ViewModelKey

@Module
abstract class CategoriesViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    abstract fun bindCategoriesViewModel(categoriesViewModel: CategoriesViewModel): ViewModel
}