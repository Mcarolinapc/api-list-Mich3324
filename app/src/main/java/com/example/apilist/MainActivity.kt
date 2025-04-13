package com.example.apilist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController
import com.example.apilist.Navigation.AppNavigation
import com.example.apilist.Navigation.Destinacion
import com.example.apilist.Navigation.NavigationItem
import com.example.apilist.ui.screens.BaseScreen
import com.example.apilist.ui.theme.APIListTheme
import com.example.apilist.viewmodel.ViewModelTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val mainViewModel: ViewModelTheme by viewModels()
        setContent {
            APIListTheme(darkTheme = mainViewModel.isDarkTheme.value) {
               BaseScreen(
                   onToggleTheme = { mainViewModel.toggleTheme() }
               )
            }
        }
    }
}
