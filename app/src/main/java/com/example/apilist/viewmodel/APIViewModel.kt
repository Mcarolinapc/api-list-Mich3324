package com.example.apilist.viewmodel

import androidx.annotation.OptIn
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.apilist.data.database.CharacterEntity
import com.example.apilist.data.model.Data
import com.example.apilist.data.model.DataPersonaje
import com.example.apilist.data.model.Personaje
import com.example.apilist.data.network.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class APIViewModel : ViewModel() {
    private val repository = Repository()
    private val _characters = MutableLiveData<Data>()
    val characters = _characters
    private val _showLoading = MutableLiveData<Boolean>(true)
    val showloading = _showLoading
    private var _personaje = MutableLiveData<DataPersonaje>()
    val personaje = _personaje


    //Lista de characters de la base de dades
    private val _favorites = MutableLiveData<MutableList<CharacterEntity>>()
    val favorites = _favorites

    //Indica si he de pintar el cor de la pantalla Detail (és favorit) o no (no és favorit)
    private val _isFavorite = MutableLiveData<Boolean>(false)
    val isFavorite = _isFavorite

    //Per mostrar un Toast quan es guardi/elimini un character de Favorits
    private val _showToast = MutableLiveData<Boolean>(false)
    val showToast = _showToast

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage




    @OptIn(UnstableApi::class)
    fun getCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getAllCharacters()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _characters.value = response.body()
                    _showLoading.value = false
                } else {
                    Log.e("Error :", response.message())
                }
            }
        }
    }

    init {
        Personaje()
    }


    @OptIn(UnstableApi::class)
    fun getCharactersbyId(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCharacterById(id)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _personaje.value = response.body()
                    _showLoading.value = false

                    // 🔥 Aquí comprobamos si ya está en favoritos
                    CoroutineScope(Dispatchers.IO).launch {
                        val fav = repository.isFavorite(id)
                        withContext(Dispatchers.Main) {
                            _isFavorite.value = fav != null
                        }
                    }

                } else {
                    Log.e("Error :", response.message())
                }
            }
        }
    }

    fun getFavorites() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getFavorites()
            withContext(Dispatchers.Main) {
                _favorites.value = response
                _showLoading.value = false
            }
        }
    }

    /*
     * Funció que obté la id del Character actual i comprova si està a la base de dades.
     * En cas afirmatiu, realitza el DELETE d'aquest. Sino, fa un INSERT.
     */
    fun saveFavorite() {
        CoroutineScope(Dispatchers.IO).launch {
            val characterId = _personaje.value!!.data._id
            val fav = repository.isFavorite(characterId)
            if (fav == null) {
                repository.saveAsFavorite(
                    CharacterEntity(
                        _id = characterId,
                        createdAt = _personaje.value!!.data.createdAt,
                        film = _personaje.value?.data?.films?.firstOrNull() ?: "Sin película",
                        imageUrl = _personaje.value!!.data.url,
                        name = _personaje.value!!.data.name,
                        sourceUrl = _personaje.value!!.data.sourceUrl,
                        updatedAt = _personaje.value!!.data.updatedAt,
                        url = _personaje.value!!.data.url
                    )
                )
                _toastMessage.postValue("Agregado a favoritos")
            } else {
                repository.deleteFavorite(fav)
                _toastMessage.postValue("Eliminado de favoritos")
            }

            withContext(Dispatchers.Main) {
                showToast.value = true
            }
        }
        showToast.value = false
    }

    fun deleteAllFavorites() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteAllFavorites()
            withContext(Dispatchers.Main) {
                _favorites.value = mutableListOf()
                _toastMessage.value = "Todos los favoritos eliminados"
                _showToast.value = true
            }
        }
    }

}