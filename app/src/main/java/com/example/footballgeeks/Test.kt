package com.example.footballgeeks

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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.footballgeeks.ui.theme.backgroudGame
import com.example.footballgeeks.ui.theme.blueLight
import com.example.footballgeeks.ui.theme.colorNav

@Composable
fun Test(navHostController: NavHostController, modifier: Modifier = Modifier) {
    val indexes = listOf(0,1,2,3)
    val navbar = listOf("landingPage","teams","competitions","players")
    val labels = listOf("matches","teams","competitions","players")
    val emblems = listOf("","https://crests.football-data.org/2061.png", "https://crests.football-data.org/PL.png", "https://rendersdeboleiro.wordpress.com/wp-content/uploads/2013/03/untitled-1-copy29.png")
    LazyRow(modifier = modifier.fillMaxWidth().padding(8.dp)) {
        items(indexes) { current ->
            Box(modifier = modifier.background(colorNav, shape = RoundedCornerShape(4.dp)).padding(8.dp).clickable {navHostController.navigate(route = navbar[current])}) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
                Image(
                    painter = rememberAsyncImagePainter(emblems[current]),
                    contentDescription = null, // Descrição para acessibilidade
                    modifier = Modifier
                        .size(72.dp) // Tamanho da imagem
                    ,
                    contentScale = ContentScale.Crop
                )
                    Spacer(modifier = modifier.size(2.dp))
                    Text(text = "${labels[current].capitalize()}", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                }
            }
            Spacer(modifier = modifier.size(6.dp))

        }
    }

}