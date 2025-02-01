package com.example.footballgeeks.playersList.data.remote

import com.example.footballgeeks.common.remote.model.PersonDTO


interface RemoteDataSource {
    suspend fun getPlayer(id: String): Result<PersonDTO?>
}