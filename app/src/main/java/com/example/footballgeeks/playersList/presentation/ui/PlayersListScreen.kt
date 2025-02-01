package com.example.footballgeeks.playersList.presentation.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.footballgeeks.playersList.presentation.PlayersListViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.footballgeeks.ui.theme.colorNav
import kotlin.random.Random

@Composable
fun PlayersListScreen(playersListViewModel: PlayersListViewModel, navHostController: NavHostController, modifier: Modifier = Modifier) {
    PlayersListContent(navHostController)
}

@Composable
fun PlayersListContent(navHostController: NavHostController, modifier: Modifier = Modifier) {

    Column(modifier = Modifier
    .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorNav)
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back Button"
                        )
                    }
                }

            }
        }
        val emblems = "https://i.pinimg.com/736x/17/94/51/1794517c612b1abd4a8b7eac83dfdfb8.jpg"
        Spacer(modifier = modifier.size(52.dp))
        Button(onClick = {
            val num = Random.nextInt(0,1000)
            navHostController.navigate(route = "players/$num") }) {
            Text("Random Player")
        }
        Spacer(modifier = modifier.size(2.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
            Image(
                painter = rememberAsyncImagePainter(emblems),
                contentDescription = null, // Descrição para acessibilidade
                modifier = Modifier
                    .size(192.dp) // Tamanho da imagem
                ,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = modifier.size(2.dp))
        }
    }
}