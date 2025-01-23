package com.example.footballgeeks.teamsList.data.remote

import com.example.footballgeeks.common.remote.model.TeamDetails
import com.example.footballgeeks.common.remote.model.TeamsDTO
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getTeams(): Result<List<TeamDetails>?>
}