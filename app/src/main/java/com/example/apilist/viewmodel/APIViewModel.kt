package com.example.apilist.viewmodel

import androidx.annotation.OptIn
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
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
    private val  _characters =MutableLiveData<Data>()
    val characters = _characters
    private val  _showLoading = MutableLiveData<Boolean>(true)
    val showloading = _showLoading
    private var _personaje =MutableLiveData<DataPersonaje>()
    val personaje= _personaje

    @OptIn(UnstableApi::class)
    fun getCharacters(){
        CoroutineScope(Dispatchers.IO).launch {
            val response= repository.getAllCharacters()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    _characters.value = response.body()
                    _showLoading.value = false
                }else{
                    Log.e("Error :", response.message())
                }
            }
        }
    }
    init {
        Personaje()
    }


    @OptIn(UnstableApi::class)
    fun getCharactersbyId(id:Int){
        CoroutineScope(Dispatchers.IO).launch {
            val response= repository.getCharacterById(id)
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    _personaje.value = response.body()
                    _showLoading.value = false
                }else{
                    Log.e("Error :", response.message())
                }
            }
        }
    }

}