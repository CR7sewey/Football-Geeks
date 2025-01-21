package com.example.footballgeeks.landingPage.data.remote

import android.accounts.NetworkErrorException
import com.example.footballgeeks.common.remote.model.Match

class LandingPageRemoteDataSource(private val landingPageService: LandingPageService): RemoteDataSource {

    override suspend fun getMatches(): Result<List<Match>?> {
        return try {
            val response = landingPageService.getMatches()
            if (response.isSuccessful) {
                val matches = response.body()?.matches ?: emptyList<Match>()
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