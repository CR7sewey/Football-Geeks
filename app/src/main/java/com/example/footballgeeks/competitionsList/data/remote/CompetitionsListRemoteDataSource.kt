package com.example.footballgeeks.competitionsList.data.remote

import android.accounts.NetworkErrorException
import com.example.footballgeeks.common.remote.model.CompetitionDetails

class CompetitionsListRemoteDataSource(private val competitionsListService: CompetitionsListService): RemoteDataSource {
    override suspend fun getCompetitions(): Result<List<CompetitionDetails>?> {
            return try {
                val response = competitionsListService.getCompetitions()
                if (response.isSuccessful) {
                    val matches = response.body()?.competitions ?: emptyList<CompetitionDetails>()
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