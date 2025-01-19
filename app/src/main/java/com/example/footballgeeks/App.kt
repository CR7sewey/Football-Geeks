package com.example.footballgeeks

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.footballgeeks.landingPage.LandingPageScreen

@Composable
fun App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "entry") {
        composable(route= "entry") {
            EntryScreen(navController)
        }
        composable(route= "landingPage") {
            LandingPageScreen()
        }
    }
    
}