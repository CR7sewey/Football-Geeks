package com.example.footballgeeks.competitionDetails.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.footballgeeks.common.remote.model.CompetitionDetails
import com.example.footballgeeks.common.remote.model.CompetitionsDetailsDTO
import com.example.footballgeeks.common.remote.model.CompetitionsDetailsStandings
import com.example.footballgeeks.common.remote.model.Standings
import com.example.footballgeeks.common.remote.model.Table
import com.example.footballgeeks.ui.theme.blueLight

@Composable
fun CompetitionDetailsPageScreen(competitionDetailsViewModel: CompetitionDetailsViewModel, code:String, navController: NavHostController, modifier: Modifier = Modifier) {

    val competition by competitionDetailsViewModel.uiCompetition.collectAsState()
    competitionDetailsViewModel.fetchDataCompetition(code)
    val competitionStandings by competitionDetailsViewModel.uiCompetitionStandings.collectAsState()
    competitionDetailsViewModel.fetchDataCompetitionStandings(code)
    val errorMessage by competitionDetailsViewModel.uiErrorMessage.collectAsState()

    competition.let {
        CompetitionDetailsContent(competition, competitionStandings, navController, competitionDetailsViewModel)
    }
}

@Composable
fun CompetitionDetailsContent(competition: CompetitionsDetailsDTO?, competitionStandings: CompetitionsDetailsStandings?, navController: NavHostController,competitionDetailsViewModel: CompetitionDetailsViewModel, modifier: Modifier = Modifier) {
    var selectedTabIndex by remember{ mutableIntStateOf(0) }

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
                    competitionDetailsViewModel.cleanCodeId()
                    // competitionDetailsViewModel.cleanId()
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
        CompetitionDetailsContentDisplayed(selectedTabIndex, onTabChange = {selectedTabIndex = it})
        if (selectedTabIndex == 0) {
            val label: List<String> = listOf("Team", "G", "W","D","L","P")
            Column(modifier = modifier.padding(8.dp)) {             LabelStandingsDisplay(label)
            }
            LazyColumn(modifier = modifier.padding(8.dp)) {

                items(competitionStandings?.standings[0]?.table ?: emptyList<Table>()) { current ->
                        StandingsDisplay(current,navController)
                        Spacer(modifier = modifier.size(2.dp))
                }
             }

        }
    }
}

@Composable
fun StandingsDisplay(standings: Table, navHostController: NavHostController, modifier: Modifier = Modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    if(standings.position in listOf(1,2,3,4)) {
                        blueLight
                } else {
                    Color.LightGray
                })
                .padding(4.dp).clickable {
                    navHostController.navigate(route = "teams/" + "${standings.team.id}")
                },
            verticalAlignment = Alignment.CenterVertically
        ) {


            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "${standings.position}. ",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp
            )

            Spacer(modifier = modifier.size(4.dp))
            // Checkbox e nome da equipa
            // Imagem da equipa
            Image(
                painter = rememberAsyncImagePainter(standings.team.crest),
                contentDescription = null, // Descrição para acessibilidade
                modifier = Modifier
                    .size(26.dp) // Tamanho da imagem
                    ,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = modifier.size(4.dp))
            Text(
                text = standings.team.tla,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.weight(4f))
            Text(
                text = "${standings.playedGames}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${standings.won}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${standings.draw}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${standings.lost}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${standings.points}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.width(16.dp))

        }

}

@Composable
fun LabelStandingsDisplay(label: List<String>, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp).background(Color.DarkGray)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "Pl ",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            color = Color.White
        )

        Spacer(modifier = modifier.size(2.dp))

        // Checkbox e nome da equipa
        // Imagem da equipa

        Text(
            text = label[0],
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.weight(4f))
        Text(
            text = label[1],
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = label[2],
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = label[3],
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = label[4],
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = label[5],
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.width(16.dp))

    }

}


@Composable
fun CompetitionDetailsContentDisplayed(selectedTabIndex: Int, onTabChange: (it: Int) -> Unit, modifier: Modifier = Modifier) {
    val tabs = listOf("Current Season", "Previous Seasons")

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