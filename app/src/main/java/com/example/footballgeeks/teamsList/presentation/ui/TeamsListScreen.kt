package com.example.footballgeeks.teamsList.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.footballgeeks.teamsList.presentation.TeamsListViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun TeamsListScreen(teamsListViewModel: TeamsListViewModel, navHostController: NavHostController, modifier: Modifier = Modifier) {
    val uiTeams by teamsListViewModel.uiTeams.collectAsState()

    Column {
        LazyColumn {
            items(uiTeams.list) { current ->
                Text(current.name)
            }
        }
    }
}

