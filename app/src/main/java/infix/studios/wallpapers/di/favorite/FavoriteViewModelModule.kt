package infix.studios.wallpapers.di.favorite

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import infix.studios.wallpapers.di.ViewModelKey
import infix.studios.wallpapers.favorite.FavoriteViewModel

@Module
abstract class FavoriteViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(favoriteViewModel: FavoriteViewModel): ViewModel
}