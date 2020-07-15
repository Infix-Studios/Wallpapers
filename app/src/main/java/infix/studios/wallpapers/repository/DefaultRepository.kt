package infix.studios.wallpapers.repository

import infix.studios.wallpapers.data.Service
import infix.studios.wallpapers.model.Photo
import infix.studios.wallpapers.util.Resource
import infix.studios.wallpapers.util.ResponseHandler
import javax.inject.Singleton

@Singleton
class DefaultRepository(val responseHandler: ResponseHandler, val service: Service) : Repository {

    override suspend fun getPhotos(): Resource<Photo> {
        TODO("Not yet implemented")
    }

}