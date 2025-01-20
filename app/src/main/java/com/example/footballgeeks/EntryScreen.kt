package com.example.footballgeeks

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun EntryScreen(navHostController: NavHostController, modifier: Modifier = Modifier) {
    EntryContent(navHostController)
}

@Composable
private fun EntryContent(navHostController: NavHostController, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.entry_image),
            contentDescription = "Onboarding Image",
            contentScale = ContentScale.Crop,
            modifier = modifier.matchParentSize()
        )
        Surface(
            color = Color.Black.copy(alpha = 0.3f),
            modifier = Modifier.fillMaxSize()
        ) {

            Column(modifier = Modifier.fillMaxSize().padding(28.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {

                Spacer(
                    modifier = Modifier.size(256.dp),

                    )
                Text(
                    text = "Football\nGeeks",
                    fontSize = 42.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 40.sp,
                    color = Color.LightGray,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.1.em,
                    fontFamily = FontFamily.Monospace
                )
                Spacer(
                    modifier = Modifier.size(46.dp),

                    )
                Text(
                    text = "Do you love football?",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = Color.LightGray,
                    modifier = modifier.padding(bottom = 124.dp)
                )
                Spacer(
                    modifier = Modifier.size(160.dp),

                    )

                Button(onClick =  { navHostController.navigate(route = "landingPage")},
                    modifier = Modifier
                        .padding(bottom = 32.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                    )

                ) {
                    Image(
                        painter = painterResource(R.drawable.symbol),
                        contentDescription = "Onboarding Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(52.dp),

                    )
                }
            }
        }
    }
}