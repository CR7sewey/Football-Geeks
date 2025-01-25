package com.example.footballgeeks.competitionsList.data.remote

import com.example.footballgeeks.common.remote.model.CompetitionsDTO
import retrofit2.Response
import retrofit2.http.GET

interface CompetitionsListService {
    @GET("competitions")
    suspend fun getCompetitions(): Response<CompetitionsDTO>
}