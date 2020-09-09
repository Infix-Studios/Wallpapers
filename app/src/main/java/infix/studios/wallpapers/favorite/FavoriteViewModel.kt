package infix.studios.wallpapers.favorite

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import infix.studios.wallpapers.repository.DefaultRepository
import infix.studios.wallpapers.util.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val repository: DefaultRepository) : ViewModel() {

    private val _favorites = getFavoritePhotos()
    val favorites = _favorites

    // Fix the empty state
    val empty = Transformations.map(_favorites) {
        it.data?.isEmpty()
    }

    init {
        getFavoritePhotos()
    }

    private fun getFavoritePhotos() = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val getPhotosList = repository.getFavoritePhotos()
        emit(getPhotosList)
    }
}