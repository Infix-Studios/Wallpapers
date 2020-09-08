package infix.studios.wallpapers.categories.categorylistdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import infix.studios.wallpapers.model.PhotoSearch
import javax.inject.Inject

class CategoryListDetailsViewModel @Inject constructor() : ViewModel() {
    private val _photo = MutableLiveData<String>()
    val photo: LiveData<String> = _photo

    fun setPhoto(photo: String) {
        _photo.value = photo
    }
}