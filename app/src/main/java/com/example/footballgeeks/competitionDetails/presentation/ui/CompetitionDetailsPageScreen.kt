package com.example.footballgeeks.competitionDetails.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.footballgeeks.competitionDetails.presentation.CompetitionDetailsViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.footballgeeks.common.remote.model.CompetitionsDetailsDTO

@Composable
fun CompetitionDetailsPageScreen(competitionDetailsViewModel: CompetitionDetailsViewModel, code:String, navController: NavHostController, modifier: Modifier = Modifier) {

    val competition by competitionDetailsViewModel.uiCompetition.collectAsState()
    competitionDetailsViewModel.fetchDataCompetition(code)
    val competitionStandings by competitionDetailsViewModel.uiCompetitionStandings.collectAsState()
    competitionDetailsViewModel.fetchDataCompetitionStandings(code)
    val errorMessage by competitionDetailsViewModel.uiErrorMessage.collectAsState()

    competition.let {
        CompetitionDetailsContent(competition, navController, competitionDetailsViewModel)
    }
}

@Composable
fun CompetitionDetailsContent(competition: CompetitionsDetailsDTO?, navController: NavHostController,competitionDetailsViewModel: CompetitionDetailsViewModel, modifier: Modifier = Modifier) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(6.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    competitionDetailsViewModel.cleanCode()
                    navController.popBackStack()
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = rememberAsyncImagePainter(competition?.emblem),
                        contentDescription = null, // Descrição para acessibilidade
                        modifier = Modifier
                            .size(72.dp) // Tamanho da imagem
                        ,
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = modifier.height(12.dp))
                    Text(
                        text = competition?.name ?: "team_name",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(42.dp))

            }
        }
    }
}