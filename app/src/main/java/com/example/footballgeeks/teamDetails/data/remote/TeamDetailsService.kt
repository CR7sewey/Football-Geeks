package com.example.footballgeeks.teamDetails.data.remote

import com.example.footballgeeks.common.remote.model.TeamDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamDetailsService {
    @GET("teams/{id}")
    suspend fun getTeamDetails(@Path("id") id: String): Response<TeamDetails>
}