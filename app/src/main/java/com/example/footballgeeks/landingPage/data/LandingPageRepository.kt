package com.example.footballgeeks.landingPage.data

import android.accounts.NetworkErrorException
import com.example.footballgeeks.common.remote.model.Match
import com.example.footballgeeks.landingPage.data.remote.LandingPageRemoteDataSource

class LandingPageRepository(private val landingPageRemoteDataSource: LandingPageRemoteDataSource) {

    suspend fun getMatches(): Result<List<Match>> {
        return try {
            val result = landingPageRemoteDataSource.getMatches()
            if (result.isSuccess) {
                val matches = result.getOrNull() ?: emptyList<Match>()
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