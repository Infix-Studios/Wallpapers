package infix.studios.wallpapers.categories.categorylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import infix.studios.wallpapers.repository.DefaultRepository
import infix.studios.wallpapers.util.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


class CategoryListViewModel @Inject constructor(private val repository: DefaultRepository): ViewModel() {
    fun searchPhotos(query: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        val getPhotosList = repository.searchPhotos(query)
        emit(getPhotosList)
    }
}