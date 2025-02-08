package com.example.footballgeeks.teamDetails.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.footballgeeks.teamDetails.presentation.TeamDetailsViewModel

@Composable
fun TeamDetailsScreen(teamDetailsViewModel: TeamDetailsViewModel = hiltViewModel<TeamDetailsViewModel>(), id: String, modifier: Modifier = Modifier) {
    // API endpoint not within permissions
}