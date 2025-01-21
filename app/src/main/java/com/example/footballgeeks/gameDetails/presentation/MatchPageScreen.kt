package com.example.footballgeeks.gameDetails.presentation

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun MatchPageScreen(id: String, navHostController: NavHostController, modifier: Modifier = Modifier) {
    Log.d("ID", id)
    Text("AQUI")
}