package infix.studios.wallpapers.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import infix.studios.wallpapers.favorite.favoritedetails.FavoriteDetailsViewModel

@Module
abstract class FavoriteDetailsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FavoriteDetailsViewModel::class)
    abstract fun bindFavoriteDetailsViewModel(favoriteDetailsViewModel: FavoriteDetailsViewModel): ViewModel
}
