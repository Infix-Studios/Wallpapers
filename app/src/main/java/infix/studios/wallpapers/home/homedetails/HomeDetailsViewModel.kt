package infix.studios.wallpapers.home.homedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import infix.studios.wallpapers.model.FavoritePhoto
import infix.studios.wallpapers.repository.DefaultRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeDetailsViewModel @Inject constructor(private val repository: DefaultRepository) : ViewModel() {

    private val _photo = MutableLiveData<String>()
    val photo: LiveData<String> = _photo

    fun setPhoto(photo: String) {
        _photo.value = photo
    }

    fun saveFavoritePhoto(favoritePhoto: FavoritePhoto) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveFavoritePhoto(favoritePhoto)
        }
    }
}