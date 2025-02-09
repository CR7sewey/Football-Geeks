package com.example.landingpage.landingPage.data

import android.accounts.NetworkErrorException
import com.example.landingpage.landingPage.data.remote.LandingPageRemoteDataSource
import com.example.landingpage.landingPage.remote.model.Match
import javax.inject.Inject

class LandingPageRepository @Inject constructor(private val landingPageRemoteDataSource: LandingPageRemoteDataSource): LandingRepository {

    override suspend fun getMatches(): Result<List<Match>> {
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