package com.example.apilist.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.apilist.ui.screens.DetalleScreen
import com.example.apilist.ui.screens.ListScreen

@Composable
fun AppNavigation(){
    val navController= rememberNavController()
    NavHost(navController,Pantalla1){
        composable<Pantalla1> {
            ListScreen{ id->navController.navigate(Pantalla2(id))}
        }

        composable<Pantalla2> {  backStackEntry ->
            val pantalla2 = backStackEntry.toRoute<Pantalla2>()
            DetalleScreen(pantalla2.id) { navController.navigate(Pantalla1) }
        }
    }
}