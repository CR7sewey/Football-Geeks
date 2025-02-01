package com.example.footballgeeks.playersList.presentation.ui

import android.app.Person
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.footballgeeks.common.remote.model.PersonDTO
import com.example.footballgeeks.playersList.presentation.PlayersListViewModel
import com.example.footballgeeks.ui.theme.colorNav
import com.example.footballgeeks.ui.theme.gamesColor
import kotlin.random.Random
import kotlin.text.capitalize

@Composable
fun PlayerDetailsScreen(playersListViewModel: PlayersListViewModel, id:String, navHostController: NavHostController, modifier: Modifier = Modifier) {
    val player by playersListViewModel.uiPlayers.collectAsState()
    playersListViewModel.fetchAllData(id)


    player.let {
        PlayerListContent(player, navHostController)
    }

}

@Composable
fun PlayerListContent(player: PersonDTO? , navHostController: NavHostController, modifier: Modifier = Modifier) {

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
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            PlayerRow("ID:  ", player?.id.toString())
            PlayerRow("Name:  ", player?.name.toString())
            PlayerRow("First Name:  ", player?.firstName.toString())
            PlayerRow("Last Name:  ", player?.lastName.toString())
            PlayerRow("Date of Birth:  ", player?.dateOfBirth.toString())
            PlayerRow("Nationality:  ", player?.nationality.toString())
            PlayerRow("Position:  ", player?.position.toString())
            PlayerRow("Shirt Number:   ", player?.shirtNumber.toString())
            PlayerRow("Team Name:   ", player?.currentTeam?.shortName.toString())
            TeamRow("Team Emblem:   ", player?.currentTeam?.crest.toString())


        }

    }
}

@Composable
fun PlayerRow(label: String, content: String, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(gamesColor, shape = RoundedCornerShape(16.dp)).padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically) {


        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = label ?: "Searching...",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = content ?: "Searching...",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

    }
    Spacer(modifier = Modifier.size(8.dp))
}

@Composable
fun TeamRow(label: String, content: String, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(gamesColor, shape = RoundedCornerShape(16.dp)).padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically) {


        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = label ?: "Searching...",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = rememberAsyncImagePainter(content),
            contentDescription = null, // Descrição para acessibilidade
            modifier = Modifier
                .size(26.dp) // Tamanho da imagem
                .padding(end = 4.dp),
            contentScale = ContentScale.Crop
        )

    }
    Spacer(modifier = Modifier.size(8.dp))
}