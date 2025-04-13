package com.example.apilist.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.apilist.ui.screens.Configuracion
import com.example.apilist.ui.screens.DetalleScreen
import com.example.apilist.ui.screens.FavoritesScreen
import com.example.apilist.ui.screens.ListScreen

@Composable

//Ya no creamos la variable NavControler, se la pasamos por parametro
fun AppNavigation(navController: NavHostController,onToggleTheme: () -> Unit){
    NavHost(navController, Destinacion.Pantalla1){
        composable<Destinacion.Pantalla1> {
            ListScreen{ id->navController.navigate(Destinacion.Pantalla2(id))}
        }

        composable<Destinacion.Pantalla2> { backStackEntry ->
            val pantalla2 = backStackEntry.toRoute<Destinacion.Pantalla2>()
            DetalleScreen(pantalla2.id) { navController.navigate(Destinacion.Pantalla1) }
        }

        composable<Destinacion.Pantalla3> {
            FavoritesScreen()
        }
        composable<Destinacion.Pantalla4> {
            Configuracion(onToggleTheme = onToggleTheme)
        }

    }
}