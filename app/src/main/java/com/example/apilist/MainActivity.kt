package com.example.apilist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.apilist.ui.screens.BaseScreen
import com.example.apilist.ui.theme.APIListTheme
import com.example.apilist.viewmodel.ViewModelTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModelTheme: ViewModelTheme by viewModels()
        setContent {
            val isDarkTheme by viewModelTheme.isDarkTheme.collectAsState()

            APIListTheme(darkTheme = isDarkTheme) {
               BaseScreen()
            }
        }
    }
}
