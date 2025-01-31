package com.example.footballgeeks.playersList.data.remote

import com.example.footballgeeks.common.remote.model.PersonDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayersListService {
    @GET("players/{id}")
    suspend fun getPlayer(@Path("id") id: String): Response<PersonDTO>
}