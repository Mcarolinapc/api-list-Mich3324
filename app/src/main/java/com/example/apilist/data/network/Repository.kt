package com.example.apilist.data.network

import com.example.apilist.CharacterApplication
import com.example.apilist.data.database.CharacterEntity

class Repository {
     val apiInterface = ApiInterface.create()
    suspend fun getAllCharacters()=apiInterface.getData()
    suspend fun getCharacterById(id:Int) = apiInterface.getCharacterById(id)

    val daoInterface = CharacterApplication.database.characterDao()

    //Database functions
    suspend fun saveAsFavorite(character: CharacterEntity) = daoInterface.addCharacter(character)
    suspend fun deleteFavorite(character: CharacterEntity) = daoInterface.deleteCharacter(character)
    suspend fun isFavorite(characterId: Int) = daoInterface.getCharacterById(characterId)
    suspend fun getFavorites() = daoInterface.getAllCharacters()
    suspend fun deleteAllFavorites() = daoInterface.deleteAll()



}