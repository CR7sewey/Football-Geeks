package com.example.footballgeeks.teamsList.data

import com.example.footballgeeks.common.remote.model.TeamDetails

interface TeamListRepositoryInterface {
    suspend fun getTeams(): Result<List<TeamDetails>?>
}