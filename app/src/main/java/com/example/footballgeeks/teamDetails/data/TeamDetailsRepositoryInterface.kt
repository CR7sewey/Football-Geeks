package com.example.footballgeeks.teamDetails.data

import com.example.footballgeeks.common.remote.model.TeamDetails

interface TeamDetailsRepositoryInterface {
    suspend fun getTeamDetails(id: String): Result<TeamDetails>
}