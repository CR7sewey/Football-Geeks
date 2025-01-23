package com.example.footballgeeks.gameDetails.presentation.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.footballgeeks.common.remote.model.Match
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

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
    var selectedTabIndex by remember{ mutableIntStateOf(0) }

    Column {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.LightGray)
        .padding(6.dp),
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
            Spacer(modifier = Modifier.height(42.dp))

        }
        // Pontuação
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "HT:  ${uiCurrentGame?.score?.halfTime?.home} - ${uiCurrentGame?.score?.halfTime?.away}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "FT:  ${uiCurrentGame?.score?.fullTime?.home} - ${uiCurrentGame?.score?.fullTime?.away}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
        MatchDetailsContent(selectedTabIndex, onTabChange = {selectedTabIndex = it})
}
}

@Composable
fun MatchDetailsContent(selectedTabIndex: Int, onTabChange: (it: Int) -> Unit, modifier: Modifier = Modifier) {
    val tabs = listOf("Details", "Lineup", "Statistics")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabs.forEachIndexed { index, tab ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clickable { onTabChange.invoke(index) }
            ) {
                // Texto da aba
                Text(
                    text = tab,
                    fontSize = 16.sp,
                    color = if (index == selectedTabIndex) Color.Blue else Color.LightGray,
                    fontWeight = if (index == selectedTabIndex) FontWeight.Bold else FontWeight.Normal
                )

                // Indicador de seleção
                if (index == selectedTabIndex) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(2.dp)
                            .background(Color.Blue)
                    )
                } else {
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }
    }
    
}