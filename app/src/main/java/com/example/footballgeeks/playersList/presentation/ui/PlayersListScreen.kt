package com.example.footballgeeks.playersList.presentation.ui

import android.util.Log
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.footballgeeks.ui.theme.blueLight
import com.example.footballgeeks.ui.theme.colorNav
import com.example.footballgeeks.ui.theme.transparent
import kotlin.random.Random

data class Player(
    val playerName: String,
    val playerPhoto: String,
    val id: String
)

@Composable
fun PlayersListScreen(playersListViewModel: PlayersListViewModel, navHostController: NavHostController, modifier: Modifier = Modifier) {
    val players = listOf<Player>(
        Player("Ronaldo", "https://e1.pngegg.com/pngimages/986/193/png-clipart-cristiano-ronaldo-thumbnail.png", "44"),
        Player("Neymar", "https://e1.pngegg.com/pngimages/55/265/png-clipart-neymar-jr.png", "44"),
        Player("Messi", "https://i.pinimg.com/564x/53/56/dc/5356dc7d6816917124e59312b1671d40.jpg", "44"),
        Player("Ronaldo", "https://img.a.transfermarkt.technology/portrait/header/8198-1694609670.jpg?lm=1", "44"),
    )
    PlayersListContent(navHostController, players)
}

@Composable
fun PlayersListContent(navHostController: NavHostController, player: List<Player>, modifier: Modifier = Modifier) {

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
        Spacer(modifier = modifier.size(52.dp))
        Button(onClick = {
            val num = Random.nextInt(0,1000)
            navHostController.navigate(route = "players/$num") }) {
            Text("Random Player")
        }
        Spacer(modifier = modifier.size(2.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(player) { current ->
                PlayerCard(current, onClick = { it -> navHostController.navigate(route = "players/$it")})
            }
        }

        /*Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
            Image(
                painter = rememberAsyncImagePainter(player[0].playerPhoto),
                contentDescription = null, // Descrição para acessibilidade
                modifier = Modifier
                    .size(80.dp)
                    .background(transparent, RoundedCornerShape(6.dp)) // Tamanho da imagem
                ,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = modifier.size(2.dp))
        }*/
    }
}

@Composable
fun PlayerCard(current: Player, onClick: (id: String) -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(blueLight, shape = RoundedCornerShape(16.dp))
            .size(100.dp).clickable { onClick.invoke(current.id)},
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(current.playerPhoto),
            contentDescription = null, // Descrição para acessibilidade
            modifier = Modifier
                .size(80.dp)
                .background(transparent, RoundedCornerShape(6.dp)) // Tamanho da imagem
            ,
            contentScale = ContentScale.Crop
        )
    }
}