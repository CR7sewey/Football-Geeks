package com.example.footballgeeks

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.footballgeeks.gameDetails.presentation.MatchDetailsViewModel
import com.example.footballgeeks.gameDetails.presentation.ui.MatchPageScreen
import com.example.footballgeeks.landingPage.presentation.LandingPageViewModel
import com.example.footballgeeks.landingPage.presentation.ui.LandingPageScreen
import androidx.compose.runtime.getValue
import com.example.footballgeeks.competitionDetails.presentation.CompetitionDetailsViewModel
import com.example.footballgeeks.competitionDetails.presentation.ui.CompetitionDetailsPageScreen
import com.example.footballgeeks.competitionsList.presentation.CompetitionsListViewModel
import com.example.footballgeeks.competitionsList.presentation.ui.CompetitionsScreen
import com.example.footballgeeks.playersList.presentation.PlayersListViewModel
import com.example.footballgeeks.playersList.presentation.ui.PlayerDetailsScreen
import com.example.footballgeeks.playersList.presentation.ui.PlayersListScreen
import com.example.footballgeeks.teamDetails.presentation.TeamDetailsViewModel
import com.example.footballgeeks.teamDetails.presentation.ui.TeamDetailsScreen
import com.example.footballgeeks.teamsList.presentation.TeamsListViewModel
import com.example.footballgeeks.teamsList.presentation.ui.TeamsListScreen

@Composable
fun App(
        teamsListViewModel: TeamsListViewModel,
        teamDetailsViewModel: TeamDetailsViewModel,
        competitionsListViewModel: CompetitionsListViewModel,
        competitionDetailsViewModel: CompetitionDetailsViewModel,
        playersListViewModel: PlayersListViewModel,
        modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    NavHost(navController = navController, startDestination = "entry") {
        /*if (navBackStackEntry?.destination?.route == "entry")
        {

        }
        else {

        }*/
        composable(route= "entry") {
            EntryScreen(navController)
        }
        composable(route= "landingPage") {
            Column {
                Test(navController)
                LandingPageScreen(navHostController = navController)
            }
        }
        composable(route= "match" + "/{id}", arguments = listOf(navArgument("id"){type = NavType.StringType})) { backStateEntry ->
            Column {
            Test(navController)
            MatchPageScreen(id = requireNotNull(backStateEntry.arguments?.getString("id").toString()), navHostController = navController)}
        }
        composable(route= "teams") {
            TeamsListScreen(teamsListViewModel, navController)
        }
        composable(route= "teams" + "/{id}", arguments = listOf(navArgument("id"){type = NavType.StringType})) { backStateEntry ->
            Test(navController)
            TeamDetailsScreen(teamDetailsViewModel, requireNotNull(backStateEntry.arguments?.getString("id").toString()))
        }
        composable(route= "competitions") {
            CompetitionsScreen(competitionsListViewModel, navController)
        }
        composable(route= "competitions" + "/{code}", arguments = listOf(navArgument("code"){type = NavType.StringType})) { backStateEntry ->
            CompetitionDetailsPageScreen(competitionDetailsViewModel, requireNotNull(backStateEntry.arguments?.getString("code").toString()), navController)
        }
        composable(route= "players") {
            PlayersListScreen(playersListViewModel, navController)
        }
        composable(route= "players" + "/{id}", arguments = listOf(navArgument("id"){type = NavType.StringType})) { backStateEntry ->
            PlayerDetailsScreen(playersListViewModel, requireNotNull(backStateEntry.arguments?.getString("id").toString()), navController)
        }
    }
    
}