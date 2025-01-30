package com.example.footballgeeks.competitionDetails.data.remote

import android.accounts.NetworkErrorException
import com.example.footballgeeks.common.remote.model.CompetitionsDetailsDTO
import com.example.footballgeeks.common.remote.model.CompetitionsDetailsStandings
import com.example.footballgeeks.common.remote.model.StatsPlayerDTO

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

    override suspend fun getCompetitionStandings(id: String): Result<CompetitionsDetailsStandings?> {
        return try {
            val response = competitionDetailsService.getCompetitionStandings(id)
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

    override suspend fun getCompetitionScorers(id: String, season:String): Result<StatsPlayerDTO?> {
        return try {
            val response = competitionDetailsService.getCompetitionScorers(id, season)
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