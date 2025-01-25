package com.example.footballgeeks.competitionDetails.data

import android.accounts.NetworkErrorException
import com.example.footballgeeks.common.remote.model.CompetitionsDetailsDTO
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
}