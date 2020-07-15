package infix.studios.wallpapers.data

import infix.studios.wallpapers.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("photos/")
    suspend fun getPhotos(
        @Query("client_id") client_id: String
    ): Photo

}