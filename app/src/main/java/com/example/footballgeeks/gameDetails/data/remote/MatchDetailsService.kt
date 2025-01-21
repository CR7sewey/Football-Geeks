package com.example.footballgeeks.gameDetails.data.remote

import com.example.footballgeeks.common.remote.model.Match
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchDetailsService {

    @GET("matches/{id}")
    suspend fun getMatchDetails(@Path("id") id: String): Response<Match>

}