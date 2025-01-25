package com.example.footballgeeks

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Test(navHostController: NavHostController, modifier: Modifier = Modifier) {
    val navbar = listOf("teams","competitions")
    LazyRow {
        items(navbar) { current ->
            Button(onClick = {navHostController.navigate(route = current)}) {
                Text(current)
            }
            Spacer(modifier = modifier.size(2.dp))

        }
    }

}