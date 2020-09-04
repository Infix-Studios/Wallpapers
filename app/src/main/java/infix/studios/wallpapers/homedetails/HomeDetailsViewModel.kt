package infix.studios.wallpapers.homedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import infix.studios.wallpapers.model.Photo
import javax.inject.Inject

class HomeDetailsViewModel @Inject constructor() : ViewModel() {

    private val _photo = MutableLiveData<Photo.PhotoItem>()
    val photo: LiveData<Photo.PhotoItem> = _photo

    fun setPhoto(photo: Photo.PhotoItem) {
        _photo.value = photo
    }
}