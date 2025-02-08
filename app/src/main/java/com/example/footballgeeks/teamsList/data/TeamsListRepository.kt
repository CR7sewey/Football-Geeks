package com.example.footballgeeks.teamsList.data

import android.accounts.NetworkErrorException
import com.example.footballgeeks.common.remote.model.TeamDetails
import com.example.footballgeeks.teamsList.data.remote.TeamsListRemoteDataSource
import javax.inject.Inject

class TeamsListRepository @Inject constructor(private val teamsListRemoteDataSource: TeamsListRemoteDataSource): TeamListRepositoryInterface {

    override suspend fun getTeams(): Result<List<TeamDetails>?> {
        return try {
            val result = teamsListRemoteDataSource.getTeams()
            if (result.isSuccess) {
                val matches = result.getOrNull() ?: emptyList<TeamDetails>()
                Result.success(matches)
            }
            else {
                Result.failure(NetworkErrorException(result.exceptionOrNull()?.message.toString()))
            }
        }
        catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        }
    }
}