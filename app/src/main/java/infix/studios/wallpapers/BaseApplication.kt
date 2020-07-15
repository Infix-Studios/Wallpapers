package infix.studios.wallpapers

import dagger.android.DaggerApplication
import infix.studios.wallpapers.di.AppComponent
import infix.studios.wallpapers.di.DaggerAppComponent
import timber.log.Timber

class BaseApplication: DaggerApplication() {

    override fun applicationInjector(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}