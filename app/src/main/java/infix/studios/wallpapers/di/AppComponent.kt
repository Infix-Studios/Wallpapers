package infix.studios.wallpapers.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import infix.studios.wallpapers.BaseApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
FragmentBuilderModule::class,
ViewModelFactoryModule::class,
AppModule::class])
interface AppComponent: AndroidInjector<BaseApplication> {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}