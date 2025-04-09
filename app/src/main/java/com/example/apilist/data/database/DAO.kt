package com.example.apilist.data.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

interface CharacterDao {
    @Query("SELECT * FROM CharacterEntity")
    suspend fun getAllCharacters(): MutableList<CharacterEntity>
    @Query("SELECT * FROM CharacterEntity WHERE _id = :characterId")
    suspend fun getCharacterById(characterId: Int): CharacterEntity
    @Insert
    suspend fun addCharacter(character: CharacterEntity)
    @Delete
    suspend fun deleteCharacter(character: CharacterEntity)
}
