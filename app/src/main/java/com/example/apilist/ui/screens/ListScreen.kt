package com.example.apilist.ui.screens
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.apilist.viewmodel.APIViewModel
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.apilist.R
import com.example.apilist.data.model.Data
import com.example.apilist.data.model.Info
import com.example.apilist.data.model.Personaje
import androidx.compose.foundation.layout.*


@Composable
fun ListScreen(navigateToNext: (Int) -> Unit) {
    val myViewModel: APIViewModel = viewModel()
    val characters: Data by myViewModel.characters.observeAsState(
        Data(emptyList(), Info(0, "", 0, 0))
    )
    val showloading: Boolean by myViewModel.showloading.observeAsState(true)
    val disneyFont = FontFamily(Font(R.font.waltographui))
    myViewModel.getCharacters()

    // Capa base
    Box(modifier = Modifier.fillMaxSize()) {

        // Fondo de pantalla
        Image(
            painter = painterResource(id = R.drawable.fondoapilist),
            contentDescription = "Fondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Contenido principal encima
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título
            Text(
                text = "Api By Mich",
                fontFamily = disneyFont,
                color = Color.Black,
                fontSize = 28.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (showloading) {
                CircularProgressIndicator()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f) // Esto hace que deje espacio para el título
                ) {
                    items(characters.data) { character ->
                        CharacterItem(
                            character = character,
                            onClick = { clickedCharacter ->
                                navigateToNext(clickedCharacter._id)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItem(character: Personaje, onClick: (Personaje) -> Unit) {
    val disneyFont = FontFamily(Font(R.font.waltographui))

    Card(
        border = BorderStroke(2.dp, color = Color.Black),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.7f)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable { onClick(character) }
        ) {
            Text(
                text = character.name,
                fontFamily = disneyFont,
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
