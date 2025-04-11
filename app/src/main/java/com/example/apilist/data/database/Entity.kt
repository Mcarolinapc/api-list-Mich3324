package com.example.apilist.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CharacterEntity")

data class CharacterEntity(
    @PrimaryKey
    val _id: Int = 0,
    val createdAt: String = "",
    val film: String = "",
    val imageUrl: String = "",
    val name: String = "",
    val sourceUrl: String = "",
    val updatedAt: String = "",
    val url: String = "",


)