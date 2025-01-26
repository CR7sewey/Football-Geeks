package com.example.footballgeeks.competitionDetails.data

import android.accounts.NetworkErrorException
import com.example.footballgeeks.common.remote.model.CompetitionsDetailsDTO
import com.example.footballgeeks.common.remote.model.CompetitionsDetailsStandings
import com.example.footballgeeks.common.remote.model.StatsPlayerDTO
import com.example.footballgeeks.competitionDetails.data.remote.CompetitionDetailsRemoteDataSource

class CompetitionDetailsRepository(private val competitionDetailsRemoteDataSource: CompetitionDetailsRemoteDataSource) {

    suspend fun getCompetitionDetails(code: String): Result<CompetitionsDetailsDTO> {
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

    suspend fun getCompetitionStandings(id: String): Result<CompetitionsDetailsStandings> {
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

    suspend fun getCompetitionScorers(id: String): Result<StatsPlayerDTO> {
        return try {
            val result = competitionDetailsRemoteDataSource.getCompetitionScorers(id)
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