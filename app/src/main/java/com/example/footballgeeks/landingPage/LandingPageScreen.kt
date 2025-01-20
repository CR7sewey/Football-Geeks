package com.example.footballgeeks.landingPage

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.footballgeeks.common.remote.model.MatchesDTO
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.footballgeeks.common.remote.model.Match
import com.example.footballgeeks.landingPage.data.remote.LandingPageService
import com.example.mycinema.common.data.remote.RetroFitClient
import retrofit2.Call
import retrofit2.Response

@Composable
fun LandingPageScreen(modifier: Modifier = Modifier) {

    var matches by remember { mutableStateOf<List<Match>>(emptyList()) }
    val apiService = RetroFitClient.retrofit.create(LandingPageService::class.java)
    val callMatchesDTO = apiService.getMatches()
    callMatchesDTO.enqueue(object : retrofit2.Callback<MatchesDTO> {
        override fun onResponse(
            call: Call<MatchesDTO?>,
            response: Response<MatchesDTO?>
        ) {
            println("AQUI 4")
            println(response.body())
            if (response.isSuccessful) {
                val movies = response.body()?.matches
                if (movies != null) {
                    matches = movies
                    println("AQUI 3")
                }
            }
            else {
                println(response.toString())
                Log.d("MainActivity", "Request Error :: ${response.errorBody()}")
            }
        }

        override fun onFailure(call: Call<MatchesDTO?>, t: Throwable) {
            Log.d("MainActivity", "Network Error :: ${t.message}")

        }
    })
    //val test = listOf<String>("1","2")
    Log.d("RESPOSTA", matches.toString())
    LandingScreenContent(matches)
}

@Composable
private fun LandingScreenContent(matches: List<Match>, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn {
            items(matches) { current ->
                MatchDisplay(current)
                Spacer(modifier = Modifier.size(6.dp))
            }
        }
    }
}

@Composable
private fun MatchDisplay(match: Match, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth().background(color = Color.LightGray),
        contentAlignment = Alignment.Center,

    ) {

            Row(modifier = modifier.padding(8.dp)) {
                AsyncImage(
                    model = match.homeTeam.crest,
                    contentDescription = "Home Team Image",
                    modifier = modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = match.homeTeam.name)
                Spacer(modifier = Modifier.size(12.dp))
                Text(text = match.awayTeam.name)
                Spacer(modifier = Modifier.size(4.dp))

                AsyncImage(
                    model = match.awayTeam.crest,
                    contentDescription = "Away Team Image",
                    modifier = modifier.size(14.dp)
                )
            }
    }

}

