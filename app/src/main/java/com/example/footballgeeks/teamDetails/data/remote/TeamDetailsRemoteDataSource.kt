package com.example.footballgeeks.teamDetails.data.remote

import android.accounts.NetworkErrorException
import com.example.footballgeeks.common.remote.model.TeamDetails
import javax.inject.Inject

class TeamDetailsRemoteDataSource @Inject constructor(private val teamDetailsService: TeamDetailsService): TeamDetailsDataSource {
    override suspend fun getTeamDetails(id: String): Result<TeamDetails?> {
        return try {
            val response = teamDetailsService.getTeamDetails(id)
            if (response.isSuccessful) {
                val match = response.body()
                Result.success(match)
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        }
    }
}