package infix.studios.wallpapers.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoritePhoto(val url: String,
                         @PrimaryKey(autoGenerate = true) val id: Int = 0)