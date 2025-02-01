package com.example.footballgeeks.playersList.data

import android.accounts.NetworkErrorException
import com.example.footballgeeks.common.remote.model.PersonDTO
import com.example.footballgeeks.playersList.data.remote.PlayersListRemoteDataSource

class PlayersListRepository(private val playersListRemoteDataSource: PlayersListRemoteDataSource) {
    suspend fun getPlayer(id: String): Result<PersonDTO> {
        return try {
            val response = playersListRemoteDataSource.getPlayer(id)
            if (response.isSuccess) {
                val player = response.getOrNull()
                Result.success(player)
            } else {
                Result.failure(NetworkErrorException(response.exceptionOrNull()?.message.toString()))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        } as Result<PersonDTO>
    }
}