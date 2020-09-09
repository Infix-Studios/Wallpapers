package infix.studios.wallpapers.repository

import infix.studios.wallpapers.model.FavoritePhoto
import infix.studios.wallpapers.model.PhotoItem
import infix.studios.wallpapers.model.PhotoSearch
import infix.studios.wallpapers.util.Resource

interface Repository {
    suspend fun getPhotos(): Resource<List<PhotoItem>>
    suspend fun searchPhotos(query: String): Resource<PhotoSearch>
    suspend fun getFavoritePhotos() : Resource<List<FavoritePhoto>>
    suspend fun saveFavoritePhoto(favoritePhoto: FavoritePhoto)
    suspend fun deleteFavoritePhoto(url: String)
}