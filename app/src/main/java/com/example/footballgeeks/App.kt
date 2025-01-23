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
import com.example.footballgeeks.teamsList.presentation.TeamsListViewModel
import com.example.footballgeeks.teamsList.presentation.ui.TeamsListScreen

@Composable
fun App(landingPageViewModel: LandingPageViewModel, matchDetailsViewModel: MatchDetailsViewModel, teamsListViewModel: TeamsListViewModel, modifier: Modifier = Modifier) {
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
                Test()
                LandingPageScreen(landingPageViewModel, navController)
            }
        }
        composable(route= "match" + "/{id}", arguments = listOf(navArgument("id"){type = NavType.StringType})) { backStateEntry ->
            Test()
            MatchPageScreen(matchDetailsViewModel, requireNotNull(backStateEntry.arguments?.getString("id").toString()), navController)
        }
        composable(route= "teams") {

            TeamsListScreen(teamsListViewModel, navController)

        }
    }
    
}