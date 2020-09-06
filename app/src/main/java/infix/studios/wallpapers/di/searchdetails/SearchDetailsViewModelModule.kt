package infix.studios.wallpapers.di.searchdetails

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import infix.studios.wallpapers.di.ViewModelKey
import infix.studios.wallpapers.search.SearchViewModel
import infix.studios.wallpapers.search.searchdetails.SearchDetailsViewModel

@Module
abstract class SearchDetailsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchDetailsViewModel::class)
    abstract fun bindSearchDetailsViewModel(searchDetailsViewModel: SearchDetailsViewModel): ViewModel
}