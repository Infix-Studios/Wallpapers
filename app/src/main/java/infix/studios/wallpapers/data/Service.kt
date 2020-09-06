package infix.studios.wallpapers.data

import infix.studios.wallpapers.model.Photo
import infix.studios.wallpapers.model.PhotoSearch
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("photos/")
    suspend fun getPhotos(
        @Query("client_id") client_id: String,
        @Query("per_page") per_page: Int
    ): Photo

    @GET("/search/photos/")
    suspend fun searchPhotos(
            @Query("client_id") client_id: String,
            @Query("query") query: String,
            @Query("per_page") per_page: Int
    ): PhotoSearch
}