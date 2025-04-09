package com.example.apilist.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CharacterEntity")

data class CharacterEntity(
    val __v: Int = 0,
    @PrimaryKey
    val _id: Int = 0,
    val allies: List<Any> = emptyList(),
    val createdAt: String = "",
    val enemies: List<Any> = emptyList(),
    val films: List<String> = emptyList(),
    val imageUrl: String = "",
    val name: String = "",
    val parkAttractions: List<String> = emptyList(),
    val shortFilms: List<Any> = emptyList(),
    val sourceUrl: String = "",
    val tvShows: List<String> = emptyList(),
    val updatedAt: String = "",
    val url: String = "",
    val videoGames: List<String> = emptyList()


)