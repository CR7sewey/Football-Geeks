package com.example.footballgeeks.competitionsList.data

import android.accounts.NetworkErrorException
import com.example.footballgeeks.common.remote.model.CompetitionDetails
import com.example.footballgeeks.competitionsList.data.remote.CompetitionsListRemoteDataSource
import javax.inject.Inject

class CompetitionsListRepository @Inject constructor(private val competitionsListRemoteDataSource: CompetitionsListRemoteDataSource): CompetitionListRepositoryInterface {
    override suspend fun getCompetitions(): Result<List<CompetitionDetails>?> {
        return try {
            val result = competitionsListRemoteDataSource.getCompetitions()
            if (result.isSuccess) {
                val matches = result.getOrNull() ?: emptyList<CompetitionDetails>()
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