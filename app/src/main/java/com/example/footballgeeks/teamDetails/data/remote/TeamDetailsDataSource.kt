package com.example.footballgeeks.teamDetails.data.remote

import com.example.footballgeeks.common.remote.model.TeamDetails

interface TeamDetailsDataSource {
    suspend fun getTeamDetails(id: String): Result<TeamDetails?>
}