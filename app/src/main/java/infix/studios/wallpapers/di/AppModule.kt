package infix.studios.wallpapers.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import infix.studios.wallpapers.data.Service
import infix.studios.wallpapers.data.local.FavoritePhotoDao
import infix.studios.wallpapers.data.local.FavoritePhotoDatabase
import infix.studios.wallpapers.repository.DefaultRepository
import infix.studios.wallpapers.repository.Repository
import infix.studios.wallpapers.util.BASE_URL
import infix.studios.wallpapers.util.DATABASE_NAME
import infix.studios.wallpapers.util.ResponseHandler
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideFavoritePhotoDao(databaseFavorite: FavoritePhotoDatabase): FavoritePhotoDao {
        return databaseFavorite.getPhotoDao()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideFavoritePhotoDatabase(context: Context): FavoritePhotoDatabase {
        return Room.databaseBuilder(context,
            FavoritePhotoDatabase::class.java, DATABASE_NAME).build()
    }


    @JvmStatic
    @Singleton
    @Provides
    fun provideFeedService(): Service {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .build()
            .create(Service::class.java)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRepository(responseHandler: ResponseHandler, service: Service, favoritePhotoDao: FavoritePhotoDao): Repository {
        return DefaultRepository(responseHandler = responseHandler,
            service = service,
            favoritePhotoDao = favoritePhotoDao)
    }

}