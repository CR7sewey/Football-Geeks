package com.example.footballgeeks

import android.app.Application
import com.example.footballgeeks.gameDetails.data.MatchDetailsRepository
import com.example.footballgeeks.gameDetails.data.remote.MatchDetailsRemoteDataSource
import com.example.footballgeeks.gameDetails.data.remote.MatchDetailsService
import com.example.footballgeeks.landingPage.data.LandingPageRepository
import com.example.footballgeeks.landingPage.data.remote.LandingPageRemoteDataSource
import com.example.footballgeeks.landingPage.data.remote.LandingPageService
import com.example.footballgeeks.teamsList.data.TeamsListRepository
import com.example.footballgeeks.teamsList.data.remote.TeamsListRemoteDataSource
import com.example.footballgeeks.teamsList.data.remote.TeamsListService
import com.example.mycinema.common.data.remote.RetroFitClient

class FootballGeeksApp: Application() {


    private val landingPageService: LandingPageService by lazy {
        RetroFitClient.retrofit.create(LandingPageService::class.java)
    }

    private val landingPageRemoteDataSource: LandingPageRemoteDataSource by lazy {
        LandingPageRemoteDataSource(landingPageService)
    }

    val landingPageRepository: LandingPageRepository by lazy {
        LandingPageRepository(landingPageRemoteDataSource)
    }
    // ---

    private val matchDetailsService: MatchDetailsService by lazy {
        RetroFitClient.retrofit.create(MatchDetailsService::class.java)
    }

    private val matchDetailsRemoteDataSource: MatchDetailsRemoteDataSource by lazy {
        MatchDetailsRemoteDataSource(matchDetailsService)
    }

    val matchDetailsRepository: MatchDetailsRepository by lazy {
        MatchDetailsRepository(matchDetailsRemoteDataSource)
    }
//---
    private val teamsListService: TeamsListService by lazy {
        RetroFitClient.retrofit.create(TeamsListService::class.java)
    }

    private val teamsListRemoteDataSource: TeamsListRemoteDataSource by lazy {
        TeamsListRemoteDataSource(teamsListService)
    }
    val teamsListRepository: TeamsListRepository by lazy {
        TeamsListRepository(teamsListRemoteDataSource)
    }

}