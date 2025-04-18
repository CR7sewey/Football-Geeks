package com.example.footballgeeks.teamDetails.data

import android.accounts.NetworkErrorException
import com.example.footballgeeks.common.remote.model.TeamDetails
import com.example.footballgeeks.teamDetails.data.remote.TeamDetailsRemoteDataSource
import javax.inject.Inject

class TeamDetailsRepository @Inject constructor(private val teamDetailsRemoteDataSource: TeamDetailsRemoteDataSource): TeamDetailsRepositoryInterface {
    override suspend fun getTeamDetails(id: String): Result<TeamDetails> {
        return try {
            val result = teamDetailsRemoteDataSource.getTeamDetails(id)
            if (result.isSuccess) {
                val team = result.getOrNull()
                Result.success(team)
            } else {
                Result.failure(NetworkErrorException(result.exceptionOrNull()?.message.toString()))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        } as Result<TeamDetails>
    }
}