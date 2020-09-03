package infix.studios.wallpapers.repository

import infix.studios.wallpapers.data.Service
import infix.studios.wallpapers.model.Photo
import infix.studios.wallpapers.util.ACCESS_KEY
import infix.studios.wallpapers.util.IMAGES_PER_PAGE
import infix.studios.wallpapers.util.Resource
import infix.studios.wallpapers.util.ResponseHandler
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException
import javax.inject.Singleton

@Singleton
class DefaultRepository(val responseHandler: ResponseHandler, val service: Service) : Repository {

    override suspend fun getPhotos(): Resource<Photo> {

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

}