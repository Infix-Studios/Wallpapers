package infix.studios.wallpapers.search.searchdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import infix.studios.wallpapers.model.PhotoSearch
import javax.inject.Inject

class SearchDetailsViewModel @Inject constructor(): ViewModel() {
    private val _photo = MutableLiveData<PhotoSearch.Result>()
    val photo: LiveData<PhotoSearch.Result> = _photo

    fun setPhoto(photo: PhotoSearch.Result) {
        _photo.value = photo
    }
}