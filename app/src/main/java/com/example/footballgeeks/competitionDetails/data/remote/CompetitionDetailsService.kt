package com.example.footballgeeks.competitionDetails.data.remote

import com.example.footballgeeks.common.remote.model.CompetitionsDetailsDTO
import com.example.footballgeeks.common.remote.model.CompetitionsDetailsStandings
import com.example.footballgeeks.common.remote.model.StatsPlayerDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CompetitionDetailsService {
    @GET("competitions/{code}")
    suspend fun getCompetitionDetails(@Path("code") code: String): Response<CompetitionsDetailsDTO>

    @GET("competitions/{id}/standings")
    suspend fun getCompetitionStandings(@Path("id") id: String): Response<CompetitionsDetailsStandings>

    @GET("competitions/{id}/scorers")
    suspend fun getCompetitionScorers(@Path("id") id: String): Response<StatsPlayerDTO>

}