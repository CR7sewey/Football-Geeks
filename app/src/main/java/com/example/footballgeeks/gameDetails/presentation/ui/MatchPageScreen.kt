package com.example.footballgeeks.gameDetails.presentation.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.footballgeeks.gameDetails.presentation.MatchDetailsViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.footballgeeks.common.remote.model.Match


@Composable
fun MatchPageScreen(matchDetailsViewModel: MatchDetailsViewModel, id: String, navHostController: NavHostController, modifier: Modifier = Modifier) {
    val uiCurrentGame by matchDetailsViewModel.uiCurrentGame.collectAsState()
    matchDetailsViewModel.fetchData(id)
    val uiErrorMessage by matchDetailsViewModel.uiErrorMessage.collectAsState()
    Log.d("ERRO", uiErrorMessage)
    Log.d("ID", id)
    uiCurrentGame.let {
        MatchScreenContent(uiCurrentGame,uiErrorMessage, matchDetailsViewModel, navHostController)
    }

}

@Composable
fun MatchScreenContent(uiCurrentGame: Match?, uiErrorMessage: String, matchDetailsViewModel: MatchDetailsViewModel, navHostController: NavHostController, modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray).padding(6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    matchDetailsViewModel.cleanId()
                    navHostController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }

                /*Text(
                modifier = Modifier.padding(start = 4.dp),
                text = movie?.title ?: "Empty title", fontSize = 48.sp,

            )*/
            }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = rememberAsyncImagePainter(uiCurrentGame?.homeTeam?.crest),
                    contentDescription = null, // Descrição para acessibilidade
                    modifier = Modifier
                        .size(72.dp) // Tamanho da imagem
                    ,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = modifier.height(12.dp))
                Text(text = uiCurrentGame?.homeTeam?.name ?: "team_name",style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = modifier.width(12.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = rememberAsyncImagePainter(uiCurrentGame?.awayTeam?.crest),
                    contentDescription = null, // Descrição para acessibilidade
                    modifier = Modifier
                        .size(72.dp) // Tamanho da imagem
                    ,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = modifier.height(12.dp))
                Text(text = uiCurrentGame?.awayTeam?.name ?: "team_name",style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(32.dp))

        }
        // Pontuação
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "${uiCurrentGame?.score?.fullTime?.home} - ${uiCurrentGame?.score?.fullTime?.away}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp
            )
        }
    }
}