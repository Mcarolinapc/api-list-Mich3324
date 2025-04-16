package com.example.apilist.viewmodel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.MutableLiveData
import com.example.apilist.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map


class ViewModelTheme(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext
    private val IS_GRID_VIEW_KEY = booleanPreferencesKey("is_grid_view")
    private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")

    private val dataStore = application.applicationContext.dataStore

    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme


    init {
        viewModelScope.launch {
            dataStore.data
                .map { prefs -> prefs[DARK_MODE_KEY] ?: false }
                .collect { dark -> _isDarkTheme.value = dark }
        }
    }

    fun toggleTheme() {
        viewModelScope.launch {
            val current = _isDarkTheme.value
            _isDarkTheme.value = !current
            dataStore.edit { prefs ->
                prefs[DARK_MODE_KEY] = !current
            }
        }

        //Activar la vista Cuadricula

    }

    val isGrid: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_GRID_VIEW_KEY] ?: true
        }

    fun setGridView(enabled: Boolean) {
        viewModelScope.launch {
            context.dataStore.edit { preferences ->
                preferences[IS_GRID_VIEW_KEY] = enabled
            }
        }
    }
}

