package infix.studios.wallpapers.di.search

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import infix.studios.wallpapers.di.ViewModelKey
import infix.studios.wallpapers.search.SearchViewModel

@Module
abstract class SearchViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel
}

