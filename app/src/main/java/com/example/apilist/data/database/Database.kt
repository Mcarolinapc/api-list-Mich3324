package com.example.apilist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

//llamada a Basede datos
@Database(entities = arrayOf(CharacterEntity::class), version = 1)
    abstract class DisneyDatabase: RoomDatabase() {
        abstract fun characterDao(): CharacterDao
    }

