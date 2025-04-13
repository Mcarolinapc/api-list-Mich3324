package com.example.apilist.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.apilist.R
import com.example.apilist.data.model.DataPersonaje
import com.example.apilist.data.model.Info
import com.example.apilist.data.model.Personaje
import com.example.apilist.viewmodel.APIViewModel

@Composable

fun DetalleScreen(id: Int, navigateToNext: () -> Unit) {

    val myViewModel: APIViewModel = viewModel<APIViewModel>()
    val dataPersonaje: DataPersonaje by myViewModel.personaje.observeAsState(
        DataPersonaje(
            Personaje(),
            Info(0, "", 0, 0)
        )
    )
    val showloading: Boolean by myViewModel.showloading.observeAsState(true)
    myViewModel.getCharactersbyId(id)

    val context = LocalContext.current
    val toastMessage by myViewModel.toastMessage.observeAsState()

    LaunchedEffect(toastMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.detallefondo), // Asegúrate de tener esta imagen en res/drawable
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )



        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (showloading) {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    item {
                        CardDetallePersonaje(
                            character = dataPersonaje.data,
                            onClick = { clickedCharacter ->
                                // Aquí puedes hacer algo con el personaje clicado
                                println("Has hecho click en: ${clickedCharacter.name} ${clickedCharacter._id}")
                            }
                        )
                    }
                }
            }


            Button(onClick = { -> navigateToNext() }) {
                Text("devuelvetebb")
            }

        }
    }
}


@Composable
fun CardDetallePersonaje(character: Personaje, onClick: (Personaje) -> Unit) {
    val disneyFont = FontFamily(Font(R.font.waltographui))
    val myViewModel: APIViewModel = viewModel<APIViewModel>()
    val isFavorite: Boolean by myViewModel.isFavorite.observeAsState(false)


    Card(
        border = BorderStroke(2.dp, color = Color.Black),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.7f)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .clickable { onClick(character) }
        ) {

            // Detalles del personaje
            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Imagen del personaje usando AsyncImage
                AsyncImage(
                    model = character.imageUrl, // Aquí usa character.imageUrl si quieres cargar la imagen dinámica
                    contentDescription = character.name,
                    modifier = Modifier
                        .size(250.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground), // Imagen de placeholder
                    error = painterResource(id = R.drawable.ic_launcher_background) // Imagen de error si la carga falla
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = character.name,
                    fontFamily = disneyFont,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black
                    )

                Spacer(modifier = Modifier.height(8.dp))

                // Mostrar películas
                if (character.films.isNotEmpty()) {
                    Text(
                        text = "Películas: ${character.films.joinToString(", ")}",
                        //fontFamily = disneyFont,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Black

                    )
                }

                // Mostrar programas de TV
                if (character.tvShows.isNotEmpty()) {
                    Text(text = "Series de TV: ${character.tvShows.joinToString(", ")}", textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Black)
                }

                // Mostrar videojuegos
                if (character.videoGames.isNotEmpty()) {
                    Text(text = "Videojuegos: ${character.videoGames.joinToString(", ")}", textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Black)
                }

                IconButton(onClick = { myViewModel.saveFavorite() }) {
                    if (isFavorite) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(36.dp),
                            tint = Color.Red
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            modifier = Modifier.size(36.dp)

                        )
                    }
                }


            }

        }
    }
}





