package com.example.footballgeeks.gameDetails.data.remote

import android.accounts.NetworkErrorException
import com.example.footballgeeks.common.remote.model.Match
import javax.inject.Inject

class MatchDetailsRemoteDataSource @Inject constructor(private val matchDetailsService: MatchDetailsService): MatchDetailsDataSource {
    override suspend fun getMatchDetails(id: String): Result<Match?> {
        return try {
            val response = matchDetailsService.getMatchDetails(id)
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