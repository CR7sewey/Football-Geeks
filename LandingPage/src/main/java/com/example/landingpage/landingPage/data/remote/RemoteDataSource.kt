package com.example.landingpage.landingPage.data.remote

import com.example.landingpage.landingPage.remote.model.Match


interface RemoteDataSource {

    suspend fun getMatches(): Result<List<Match>?>
}