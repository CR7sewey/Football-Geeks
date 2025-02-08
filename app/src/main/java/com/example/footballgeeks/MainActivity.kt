package com.example.footballgeeks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.footballgeeks.competitionDetails.presentation.CompetitionDetailsViewModel
import com.example.footballgeeks.competitionsList.presentation.CompetitionsListViewModel
import com.example.footballgeeks.gameDetails.presentation.MatchDetailsViewModel
import com.example.footballgeeks.landingPage.presentation.LandingPageViewModel
import com.example.footballgeeks.playersList.presentation.PlayersListViewModel
import com.example.footballgeeks.teamDetails.presentation.TeamDetailsViewModel
import com.example.footballgeeks.teamsList.presentation.TeamsListViewModel
import com.example.footballgeeks.ui.theme.FootballGeeksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /*private val landingPageViewModel by viewModels<LandingPageViewModel> { LandingPageViewModel.Factory } */
   // private val matchDetailsViewModel by viewModels<MatchDetailsViewModel> { MatchDetailsViewModel.Factory }
   // private val teamsListViewModel by viewModels<TeamsListViewModel> { TeamsListViewModel.Factory }
    //private val teamDetailsViewModel by viewModels<TeamDetailsViewModel> { TeamDetailsViewModel.Factory }
    private val competitionsListViewModel by viewModels<CompetitionsListViewModel> { CompetitionsListViewModel.Factory }
    private val competitionDetailsViewModel by viewModels<CompetitionDetailsViewModel> { CompetitionDetailsViewModel.Factory }
    private val playersListViewModel by viewModels<PlayersListViewModel> { PlayersListViewModel.Factory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            FootballGeeksTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(

                        competitionsListViewModel,
                        competitionDetailsViewModel,
                        playersListViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}