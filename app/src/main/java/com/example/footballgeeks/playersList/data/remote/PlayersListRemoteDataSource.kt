package com.example.footballgeeks.playersList.data.remote

import android.accounts.NetworkErrorException
import com.example.footballgeeks.common.remote.model.PersonDTO

class PlayersListRemoteDataSource(private val playersListService: PlayersListService): RemoteDataSource {
    override suspend fun getPlayer(id: String): Result<PersonDTO?> {
        return try {
            val response = playersListService.getPlayer(id)
            if (response.isSuccessful) {
                val player = response.body()
                Result.success(player)
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