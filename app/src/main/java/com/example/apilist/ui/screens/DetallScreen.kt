package com.example.apilist.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.apilist.R
import com.example.apilist.data.model.DataPersonaje
import com.example.apilist.data.model.Info
import com.example.apilist.data.model.Personaje
import com.example.apilist.viewmodel.APIViewModel

@Composable

fun DetalleScreen( id:Int, navigateToNext:()->Unit){

    val myViewModel: APIViewModel = viewModel<APIViewModel>()
    val dataPersonaje: DataPersonaje by myViewModel.personaje.observeAsState(DataPersonaje(Personaje(), Info(0,"",0, 0)))
    val showloading: Boolean by myViewModel.showloading.observeAsState(true)
    myViewModel.getCharactersbyId(id)


    Column (
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        if (showloading) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(Modifier.fillMaxSize()) {
                item {
                    DetallePersonajeItem(
                        character = dataPersonaje.data,
                        onClick = { clickedCharacter ->
                            // Aquí puedes hacer algo con el personaje clicado
                            println("Has hecho click en: ${clickedCharacter.name} ${clickedCharacter._id}")
                        }
                    )
                }
            }
        }


        Button( onClick = { -> navigateToNext()}) {
            Text("devuelvetebb")
        }

    }
}


@Composable
fun DetallePersonajeItem(character: Personaje, onClick: (Personaje) -> Unit) {
    Card(
        border = BorderStroke(2.dp, color = Color.Black),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(8.dp)
            .fillMaxSize()
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
                //imagen de corazón que cuando haga click ejecute el onclick que debe de guardar en BBDD

                // Imagen del personaje usando AsyncImage
                AsyncImage(
                    model = character.imageUrl, // Aquí usa character.imageUrl si quieres cargar la imagen dinámica
                    contentDescription = character.name,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground), // Imagen de placeholder
                    error = painterResource(id = R.drawable.ic_launcher_background) // Imagen de error si la carga falla
                )

                Text(
                    text = character.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Mostrar películas
                if (character.films.isNotEmpty()) {
                    Text(text = "Películas: ${character.films.joinToString(", ")}")
                }

                // Mostrar programas de TV
                if (character.tvShows.isNotEmpty()) {
                    Text(text = "Series de TV: ${character.tvShows.joinToString(", ")}")
                }

                // Mostrar videojuegos
                if (character.videoGames.isNotEmpty()) {
                    Text(text = "Videojuegos: ${character.videoGames.joinToString(", ")}")
                }



            }
        }
    }
}

/*@Composable
fun DetallePersonajeItem(personaje: Personaje) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Imagen del personaje
        item {
            AsyncImage(
                model = personaje.imageUrl,
                contentDescription = personaje.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                error = painterResource(id = R.drawable.ic_launcher_background)
            )
        }

        //Spacer(modifier = Modifier.height(16.dp))

        // Información del personaje
        item {
            // Nombre del personaje
            Text(
                text = personaje.name,
                style = MaterialTheme.typography.titleLarge,  // Usando titleLarge en lugar de h6
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Mostrar películas
            if (personaje.films.isNotEmpty()) {
                Text("🎬 Películas", style = MaterialTheme.typography.titleMedium)  // Usando titleMedium
                Text(personaje.films.joinToString(", "), style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Mostrar cortometrajes
            if (personaje.shortFilms.isNotEmpty()) {
                Text("🎞️ Cortometrajes", style = MaterialTheme.typography.titleMedium)
                Text(personaje.shortFilms.joinToString(", "), style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Mostrar series de TV
            if (personaje.tvShows.isNotEmpty()) {
                Text("📺 Series de TV", style = MaterialTheme.typography.titleMedium)
                Text(personaje.tvShows.joinToString(", "), style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Mostrar videojuegos
            if (personaje.videoGames.isNotEmpty()) {
                Text("🎮 Videojuegos", style = MaterialTheme.typography.titleMedium)
                Text(personaje.videoGames.joinToString(", "), style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Mostrar atracciones de parque
            if (personaje.parkAttractions.isNotEmpty()) {
                Text("🎢 Atracciones de Parque", style = MaterialTheme.typography.titleMedium)
                Text(personaje.parkAttractions.joinToString(", "), style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Mostrar aliados
            if (personaje.allies.isNotEmpty()) {
                Text("🤝 Aliados", style = MaterialTheme.typography.titleMedium)
                Text(personaje.allies.joinToString(", "), style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Mostrar enemigos
            if (personaje.enemies.isNotEmpty()) {
                Text("💀 Enemigos", style = MaterialTheme.typography.titleMedium)
                Text(personaje.enemies.joinToString(", "), style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Mostrar URL
            Text("🔗 URL", style = MaterialTheme.typography.titleMedium)
            Text(personaje.url, style = MaterialTheme.typography.bodyLarge, color = Color.Blue)
        }
    }
}*/




