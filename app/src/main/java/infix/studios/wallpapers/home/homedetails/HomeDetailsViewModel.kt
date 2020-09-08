package infix.studios.wallpapers.home.homedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HomeDetailsViewModel @Inject constructor() : ViewModel() {

    private val _photo = MutableLiveData<String>()
    val photo: LiveData<String> = _photo

    fun setPhoto(photo: String) {
        _photo.value = photo
    }
}