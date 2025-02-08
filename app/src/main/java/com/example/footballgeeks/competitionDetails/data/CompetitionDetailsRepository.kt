package com.example.footballgeeks.competitionDetails.data

import android.accounts.NetworkErrorException
import com.example.footballgeeks.common.remote.model.CompetitionsDetailsDTO
import com.example.footballgeeks.common.remote.model.CompetitionsDetailsStandings
import com.example.footballgeeks.common.remote.model.StatsPlayerDTO
import com.example.footballgeeks.competitionDetails.data.remote.CompetitionDetailsRemoteDataSource
import javax.inject.Inject

class CompetitionDetailsRepository @Inject constructor(private val competitionDetailsRemoteDataSource: CompetitionDetailsRemoteDataSource): CompetitionDetailsRepositoryInterface {

    override suspend fun getCompetitionDetails(code: String): Result<CompetitionsDetailsDTO> {
        return try {
            val result = competitionDetailsRemoteDataSource.getCompetitionDetails(code)
            if (result.isSuccess) {
                val match = result.getOrNull()
                Result.success(match)
            } else {
                Result.failure(NetworkErrorException(result.exceptionOrNull()?.message.toString()))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        } as Result<CompetitionsDetailsDTO>
    }

    override suspend fun getCompetitionStandings(id: String): Result<CompetitionsDetailsStandings> {
        return try {
            val result = competitionDetailsRemoteDataSource.getCompetitionStandings(id)
            if (result.isSuccess) {
                val match = result.getOrNull()
                Result.success(match)
            } else {
                Result.failure(NetworkErrorException(result.exceptionOrNull()?.message.toString()))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        } as Result<CompetitionsDetailsStandings>
    }

    override suspend fun getCompetitionScorers(id: String, season: String): Result<StatsPlayerDTO> {
        return try {
            val result = competitionDetailsRemoteDataSource.getCompetitionScorers(id, season)
            if (result.isSuccess) {
                val match = result.getOrNull()
                Result.success(match)
            } else {
                Result.failure(NetworkErrorException(result.exceptionOrNull()?.message.toString()))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        } as Result<StatsPlayerDTO>
    }

}