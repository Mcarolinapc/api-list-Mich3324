package com.example.apilist.ui.screens

import android.icu.text.IDNA
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.apilist.viewmodel.APIViewModel
import androidx.compose.ui.text.style.TextAlign
import com.example.apilist.data.model.Data
import com.example.apilist.data.model.Info
import com.example.apilist.data.model.Personaje


@Composable
fun ListScreen(navigateToNetx:(Int)->Unit) {
    val myViewModel: APIViewModel = viewModel<APIViewModel>()
    val characters: Data by myViewModel.characters.observeAsState(Data(emptyList(), Info(0,"",0, 0)))
    val showloading: Boolean by myViewModel.showloading.observeAsState(true)
    myViewModel.getCharacters()

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
            items(characters.data) {character->
                CharacterItem(
                    character = character,
                    onClick = { clickedCharacter ->
                        // Aquí puedes hacer algo con el personaje clicado
                        println("Has hecho click en: ${clickedCharacter.name} ${clickedCharacter._id}")
                        navigateToNetx(clickedCharacter._id)
                    }
                )
            }
        }
    }
}


    @Composable
    fun CharacterItem(character: Personaje, onClick: (Personaje) -> Unit) {


        Card(
            border = BorderStroke(2.dp, color = Color.Black),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(8.dp).statusBarsPadding().background(Color.DarkGray)

        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable{ onClick(character) }
            ) {

                Text(
                    text = character.name,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize()
                )

            }
        }
    }

