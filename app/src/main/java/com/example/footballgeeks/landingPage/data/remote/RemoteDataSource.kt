package com.example.footballgeeks.landingPage.data.remote

import com.example.footballgeeks.common.remote.model.Match

interface RemoteDataSource {

    suspend fun getMatches(): Result<List<Match>?>
}