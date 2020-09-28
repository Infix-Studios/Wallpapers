package infix.studios.wallpapers.favorite.favoritedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import infix.studios.wallpapers.repository.DefaultRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteDetailsViewModel @Inject constructor(private val repository: DefaultRepository): ViewModel() {
    private val _photo = MutableLiveData<String>()
    val photo: LiveData<String> = _photo

    fun setPhoto(photo: String) {
        _photo.value = photo
    }

    fun deleteFavoritePhoto(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavoritePhoto(url)
        }
    }
}