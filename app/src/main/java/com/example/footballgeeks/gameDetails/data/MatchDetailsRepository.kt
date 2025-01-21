package com.example.footballgeeks.gameDetails.data

import android.accounts.NetworkErrorException
import com.example.footballgeeks.common.remote.model.Match
import com.example.footballgeeks.gameDetails.data.remote.MatchDetailsRemoteDataSource

class MatchDetailsRepository(private val matchDetailsRemoteDataSource: MatchDetailsRemoteDataSource) {

    suspend fun getMatchDetails(id: String): Result<Match> {
        return try {
            val result = matchDetailsRemoteDataSource.getMatchDetails(id)
            if (result.isSuccess) {
                val match = result.getOrNull()
                Result.success(match)
            } else {
                Result.failure(NetworkErrorException(result.exceptionOrNull()?.message.toString()))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        } as Result<Match>
    }
}