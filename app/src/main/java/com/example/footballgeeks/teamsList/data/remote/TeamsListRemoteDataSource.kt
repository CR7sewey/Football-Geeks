package com.example.footballgeeks.teamsList.data.remote

import android.accounts.NetworkErrorException
import com.example.footballgeeks.common.remote.model.Team
import com.example.footballgeeks.common.remote.model.TeamDetails

class TeamsListRemoteDataSource(private val teamsListService: TeamsListService): RemoteDataSource {
    override suspend fun getTeams(): Result<List<TeamDetails>?> {
            return try {
                val response = teamsListService.getTeams()
                if (response.isSuccessful) {
                    val matches = response.body()?.teams ?: emptyList<TeamDetails>()
                    Result.success(matches)
                }
                else {
                    Result.failure(NetworkErrorException(response.message()))
                }
            }
            catch (ex: Exception) {
                ex.printStackTrace()
                Result.failure(ex)
            }
        }

}