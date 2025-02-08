package com.example.footballgeeks.gameDetails.data

import com.example.footballgeeks.common.remote.model.Match

interface MatchDetailsRepositoryInterface {

    suspend fun getMatchDetails(id: String): Result<Match>
}