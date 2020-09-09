package infix.studios.wallpapers.repository

import infix.studios.wallpapers.data.Service
import infix.studios.wallpapers.model.FavoritePhoto
import infix.studios.wallpapers.data.local.FavoritePhotoDao
import infix.studios.wallpapers.model.PhotoItem
import infix.studios.wallpapers.model.PhotoSearch
import infix.studios.wallpapers.util.ACCESS_KEY
import infix.studios.wallpapers.util.IMAGES_PER_PAGE
import infix.studios.wallpapers.util.Resource
import infix.studios.wallpapers.util.ResponseHandler
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRepository @Inject constructor(val responseHandler: ResponseHandler,
                                            val service: Service,
                                            val favoritePhotoDao: FavoritePhotoDao) : Repository {

    override suspend fun getPhotos(): Resource<List<PhotoItem>> {

        try {
            val getPhotos = service.getPhotos(ACCESS_KEY, IMAGES_PER_PAGE)
            // Timber.i("\n\n**************\nRepository: ${getPhotos}")
            return responseHandler.handleSuccess(getPhotos)

        } catch(ex:Exception) {
            return when(ex) {
                is HttpException -> {
                    responseHandler.handleException((ex as HttpException).code())
                }
                is SocketTimeoutException -> {
                    // handle
                    Resource.error("Connection failed", null)
                }
                else -> Resource.error("Connection Failed", null)
            }
        }

    }

    override suspend fun searchPhotos(query: String): Resource<PhotoSearch> {
        try {
            val getPhotos = service.searchPhotos(ACCESS_KEY, query, IMAGES_PER_PAGE)
            //Timber.i("\n\n**************\nRepository Search: ${getPhotos}")
            return responseHandler.handleSuccess(getPhotos)

        } catch(ex:Exception) {
            Timber.i("\n\n**************\nRepository Search error: $ex")
            return when(ex) {
                is HttpException -> {
                    responseHandler.handleException((ex as HttpException).code())
                }
                is SocketTimeoutException -> {
                    // handle
                    Resource.error("Connection failed", null)
                }
                else -> Resource.error("Connection Failed", null)
            }
        }
    }

    override suspend fun getFavoritePhotos() : Resource<List<FavoritePhoto>> {
        try {
            //Timber.i("\n\n**************\nRepository Search: ${getPhotos}")
            return responseHandler.handleSuccess(favoritePhotoDao.getPhotosUri())

        } catch(ex:Exception) {
            Timber.i("\n\n**************\nRepository Search error: $ex")
            return when(ex) {
                is HttpException -> {
                    responseHandler.handleException((ex as HttpException).code())
                }
                is SocketTimeoutException -> {
                    // handle
                    Resource.error("Connection failed", null)
                }
                else -> Resource.error("Connection Failed", null)
            }
        }
    }

    override suspend fun saveFavoritePhoto(favoritePhoto: FavoritePhoto) {
        favoritePhotoDao.savePhotoUri(favoritePhoto)
    }

    override suspend fun deleteFavoritePhoto(url: String) {
        favoritePhotoDao.deletePhotoByUrl(url)
    }

}