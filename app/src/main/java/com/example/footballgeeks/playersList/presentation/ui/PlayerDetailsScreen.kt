package com.example.footballgeeks.playersList.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.footballgeeks.playersList.presentation.PlayersListViewModel
import com.example.footballgeeks.ui.theme.colorNav
import kotlin.text.capitalize

@Composable
fun PlayerDetailsScreen(playersListViewModel: PlayersListViewModel, id:String, navHostController: NavHostController, modifier: Modifier = Modifier) {
    val player by playersListViewModel.uiPlayers.collectAsState()
    playersListViewModel.fetchAllData(id)

    val emblems = "https://rendersdeboleiro.wordpress.com/wp-content/uploads/2013/03/untitled-1-copy29.png"

                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
                    Image(
                        painter = rememberAsyncImagePainter(emblems),
                        contentDescription = null, // Descrição para acessibilidade
                        modifier = Modifier
                            .size(72.dp) // Tamanho da imagem
                        ,
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = modifier.size(2.dp))
                }


}