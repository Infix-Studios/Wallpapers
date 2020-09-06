package infix.studios.wallpapers.di.homedetails

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import infix.studios.wallpapers.di.ViewModelKey
import infix.studios.wallpapers.home.homedetails.HomeDetailsViewModel

@Module
abstract class HomeDetailsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeDetailsViewModel::class)
    abstract fun bindHomeDetailsViewModel(homeDetailsViewModel: HomeDetailsViewModel): ViewModel
}