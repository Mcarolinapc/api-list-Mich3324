package com.example.apilist.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.apilist.Navigation.AppNavigation
import com.example.apilist.Navigation.Destinacion
import com.example.apilist.Navigation.NavigationItem


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BaseScreen() {
    var selectedItem: Int by remember { mutableIntStateOf(0) }
    val items = listOf(
        NavigationItem("Home", Icons.Default.Home, Destinacion.Pantalla1, 0),
        NavigationItem("Favoritos", Icons.Default.Favorite, Destinacion.Pantalla3, 1),
        NavigationItem("Configuración", Icons.Default.Settings, Destinacion.Pantalla4, 2)
    )
    val navController = rememberNavController()

    Scaffold(bottomBar = {
        NavigationBar {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = item.index == selectedItem,
                    label = { Text(item.label) },
                    icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                    onClick = {
                        selectedItem = index
                        navController.navigate(item.route)
                    }
                )
            }
        }
    }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            AppNavigation(navController = navController)
        }
    }

}