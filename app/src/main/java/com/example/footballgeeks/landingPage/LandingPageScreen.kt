package com.example.footballgeeks.landingPage

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.footballgeeks.common.remote.model.MatchesDTO
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.footballgeeks.common.remote.model.Match
import com.example.footballgeeks.landingPage.data.remote.LandingPageService
import com.example.mycinema.common.data.remote.RetroFitClient
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

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
    Log.d("RESPOSTA", matches.toString())
    Text("Hello")
}