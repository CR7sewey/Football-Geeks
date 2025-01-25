package com.example.footballgeeks.competitionDetails.data.remote

import com.example.footballgeeks.common.remote.model.CompetitionsDetailsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CompetitionDetailsService {
    @GET("competitions/{code}")
    suspend fun getCompetitionDetails(@Path("code") code: String): Response<CompetitionsDetailsDTO>
}