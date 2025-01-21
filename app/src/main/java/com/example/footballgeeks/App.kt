package com.example.footballgeeks

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.footballgeeks.gameDetails.presentation.MatchDetailsViewModel
import com.example.footballgeeks.gameDetails.presentation.ui.MatchPageScreen
import com.example.footballgeeks.landingPage.presentation.LandingPageViewModel
import com.example.footballgeeks.landingPage.presentation.ui.LandingPageScreen

@Composable
fun App(landingPageViewModel: LandingPageViewModel, matchDetailsViewModel: MatchDetailsViewModel, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "entry") {
        composable(route= "entry") {
            EntryScreen(navController)
        }
        composable(route= "landingPage") {
            LandingPageScreen(landingPageViewModel, navController)
        }
        composable(route= "match" + "/{id}", arguments = listOf(navArgument("id"){type = NavType.StringType})) { backStateEntry ->

            MatchPageScreen(matchDetailsViewModel, requireNotNull(backStateEntry.arguments?.getString("id").toString()), navController)
        }
    }
    
}