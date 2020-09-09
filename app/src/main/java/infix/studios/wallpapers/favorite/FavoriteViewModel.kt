package infix.studios.wallpapers.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import infix.studios.wallpapers.repository.DefaultRepository
import infix.studios.wallpapers.util.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val repository: DefaultRepository) : ViewModel() {
    val getFavoritePhotos = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val getPhotosList = repository.getFavoritePhotos()
        emit(getPhotosList)
    }
}