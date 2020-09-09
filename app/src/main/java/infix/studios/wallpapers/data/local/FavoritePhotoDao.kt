package infix.studios.wallpapers.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import infix.studios.wallpapers.model.FavoritePhoto

@Dao
interface FavoritePhotoDao {
    @Query("SELECT * from FavoritePhoto")
    suspend fun getPhotosUri(): List<FavoritePhoto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePhotoUri(favoritePhoto: FavoritePhoto)

    @Query("DELETE FROM FavoritePhoto WHERE url = :urlFromString")
    suspend fun deletePhotoByUrl(urlFromString: String)
}