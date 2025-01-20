package com.example.footballgeeks.landingPage.data.remote

import com.example.footballgeeks.common.remote.model.MatchesDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface LandingPageService {
    @GET("matches")
    suspend fun getMatches(): Response<MatchesDTO>
}