package com.example.apilist.viewmodel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map


class ViewModelTheme(application: Application) : AndroidViewModel(application) {
    private val Context.dataStore by preferencesDataStore(name = "settings")
    private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")

    private val _isDarkTheme = mutableStateOf(false)
    val isDarkTheme: State<Boolean> = _isDarkTheme

    init {
        viewModelScope.launch {
            application.applicationContext.dataStore.data
                .map { prefs -> prefs[DARK_MODE_KEY] ?: false }
                .collect { dark -> _isDarkTheme.value = dark }
        }
    }

    fun toggleTheme() {
        viewModelScope.launch {
            val current = _isDarkTheme.value
            _isDarkTheme.value = !current
            getApplication<Application>().applicationContext.dataStore.edit { prefs ->
                prefs[DARK_MODE_KEY] = !current
            }
        }
    }
}
