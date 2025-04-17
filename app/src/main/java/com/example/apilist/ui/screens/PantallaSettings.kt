package com.example.apilist.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Switch
import androidx.compose.material3.TextButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apilist.R
import com.example.apilist.viewmodel.APIViewModel
import com.example.apilist.viewmodel.ViewModelTheme

@Composable
fun Configuracion() {
    val disneyFont = FontFamily(Font(R.font.waltographui))
    var showDialog by remember { mutableStateOf(false) }

    val themeViewModel: ViewModelTheme = viewModel()
    val apiViewModel: APIViewModel = viewModel()

    val isDarkTheme by themeViewModel.isDarkTheme.collectAsState(initial = false)
    val isGrid by themeViewModel.isGrid.collectAsState(initial = true)

    val opciones = listOf("Lista", "Grid")
    val seleccionActual = if (isGrid) "Grid" else "Lista"

    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondosettings),
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center //
        ) {
            Text(
                "Configuración",
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = disneyFont,
                color = Color.Yellow,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(100.dp))

            Row(modifier = Modifier.width(220.dp),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                Icon(Icons.Default.Star, contentDescription = null, modifier = Modifier,Color.Yellow)
                Spacer(Modifier.width(8.dp))
                Text("Cambiar tema")
                Spacer(Modifier.width(8.dp))
                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = { themeViewModel.toggleTheme() }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.width(220.dp),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                Icon(Icons.Default.Star, contentDescription = null, modifier = Modifier,Color.Yellow)
                Spacer(Modifier.width(8.dp))
                Text("Vista")
                Spacer(Modifier.width(8.dp))

                Box {
                    Button(onClick = { expanded = true }) {
                        Text(seleccionActual)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        opciones.forEach { opcion ->
                            DropdownMenuItem(
                                text = { Text(opcion) },
                                onClick = {
                                    themeViewModel.setGridView(opcion == "Grid")
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(50.dp))


            Button(
                onClick = { showDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Icon(Icons.Default.Delete, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Eliminar todos los favoritos")
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("¿Estás seguro?") },
                    text = { Text("Esta acción eliminará todos los favoritos. ¿Deseas continuar?") },
                    confirmButton = {
                        TextButton(onClick = {
                            apiViewModel.deleteAllFavorites()
                            showDialog = false
                        }) {
                            Text("Sí", color = Color.Red)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Cancelar")
                        }
                    }
                )
            }


        }


    }
}
