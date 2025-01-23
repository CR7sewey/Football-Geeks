package com.example.footballgeeks.teamsList.data.remote

import com.example.footballgeeks.common.remote.model.TeamsDTO
import retrofit2.Response
import retrofit2.http.GET

interface TeamsListService {
    @GET("teams")
    suspend fun getTeams(): Response<TeamsDTO>
}