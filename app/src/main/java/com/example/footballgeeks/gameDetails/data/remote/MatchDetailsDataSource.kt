package com.example.footballgeeks.gameDetails.data.remote

import com.example.footballgeeks.common.remote.model.Match

interface MatchDetailsDataSource {

    suspend fun getMatchDetails(id: String): Result<Match?>
}