package com.example.footballgeeks.landingPage.data

import com.example.footballgeeks.common.remote.model.Match

interface LandingRepository {

    suspend fun getMatches(): Result<List<Match>>
}