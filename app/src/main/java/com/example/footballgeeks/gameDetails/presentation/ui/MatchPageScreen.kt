package com.example.footballgeeks.gameDetails.presentation.ui

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.footballgeeks.gameDetails.presentation.MatchDetailsViewModel
import androidx.compose.runtime.getValue



@Composable
fun MatchPageScreen(matchDetailsViewModel: MatchDetailsViewModel, id: String, navHostController: NavHostController, modifier: Modifier = Modifier) {
    val uiCurrentGame by matchDetailsViewModel.uiCurrentGame.collectAsState()
    matchDetailsViewModel.fetchData(id)
    Log.d("ID", id)
    Text(text = uiCurrentGame?.homeTeam?.name ?: "AQUIIIII")
}