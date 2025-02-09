package com.example.landingpage.landingPage.data

import com.example.landingpage.landingPage.remote.model.Match


interface LandingRepository {

    suspend fun getMatches(): Result<List<Match>>
}