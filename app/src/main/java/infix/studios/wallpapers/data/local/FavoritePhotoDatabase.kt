package infix.studios.wallpapers.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import infix.studios.wallpapers.model.FavoritePhoto

@Database(entities = [FavoritePhoto::class], version = 1, exportSchema = false)
abstract class FavoritePhotoDatabase : RoomDatabase() {
    abstract fun getPhotoDao(): FavoritePhotoDao
}