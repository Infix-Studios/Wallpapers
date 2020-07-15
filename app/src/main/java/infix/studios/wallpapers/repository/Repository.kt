package infix.studios.wallpapers.repository

import infix.studios.wallpapers.model.Photo
import infix.studios.wallpapers.util.Resource

interface Repository {
    suspend fun getPhotos(): Resource<Photo>
}