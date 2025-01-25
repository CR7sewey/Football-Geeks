package com.example.footballgeeks.competitionDetails.data.remote

import android.accounts.NetworkErrorException
import com.example.footballgeeks.common.remote.model.CompetitionsDetailsDTO

class CompetitionDetailsRemoteDataSource(private val competitionDetailsService: CompetitionDetailsService): CompetitionDetailsDataSource {
    override suspend fun getCompetitionDetails(code: String): Result<CompetitionsDetailsDTO?> {
            return try {
                val response = competitionDetailsService.getCompetitionDetails(code)
                if (response.isSuccessful) {
                    val match = response.body()
                    Result.success(match)
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