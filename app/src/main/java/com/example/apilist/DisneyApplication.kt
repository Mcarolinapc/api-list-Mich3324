package com.example.apilist

import android.app.Application
import androidx.room.Room
import com.example.apilist.data.database.DisneyDatabase

class CharacterApplication : Application() {
    companion object {
        lateinit var database: DisneyDatabase
    }
    override fun onCreate() {
        super.onCreate()
        database =
            Room.databaseBuilder(this, DisneyDatabase::class.java, "DisneyDatabase").build()
    }
}
