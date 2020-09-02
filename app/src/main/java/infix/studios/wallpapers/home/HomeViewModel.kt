package infix.studios.wallpapers.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import infix.studios.wallpapers.repository.DefaultRepository
import infix.studios.wallpapers.util.Resource
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: DefaultRepository): ViewModel() {
    val getPhotos = liveData(Dispatchers.IO){
        emit(Resource.loading(null))

        val getPhotosList = repository.getPhotos()
        emit(getPhotosList)
        Timber.i("\n\n**************\nViewmodel: ${getPhotosList.data}")
    }
}