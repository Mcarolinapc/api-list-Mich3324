package com.example.apilist.Navigation

import androidx.compose.ui.graphics.vector.ImageVector


    data class NavigationItem(
        val label: String,
        val icon: ImageVector,
        val route: Destinacion.Pantalla1,
        val index: Int
    )

