package com.example.footballgeeks

import android.app.Application
import com.example.footballgeeks.competitionDetails.data.CompetitionDetailsRepository
import com.example.footballgeeks.competitionDetails.data.remote.CompetitionDetailsRemoteDataSource
import com.example.footballgeeks.competitionDetails.data.remote.CompetitionDetailsService
import com.example.footballgeeks.competitionsList.data.CompetitionsListRepository
import com.example.footballgeeks.competitionsList.data.remote.CompetitionsListRemoteDataSource
import com.example.footballgeeks.competitionsList.data.remote.CompetitionsListService
import com.example.footballgeeks.gameDetails.data.MatchDetailsRepository
import com.example.footballgeeks.gameDetails.data.remote.MatchDetailsRemoteDataSource
import com.example.footballgeeks.gameDetails.data.remote.MatchDetailsService
import com.example.footballgeeks.landingPage.data.LandingPageRepository
import com.example.footballgeeks.landingPage.data.remote.LandingPageRemoteDataSource
import com.example.footballgeeks.landingPage.data.remote.LandingPageService
import com.example.footballgeeks.playersList.data.PlayersListRepository
import com.example.footballgeeks.playersList.data.remote.PlayersListRemoteDataSource
import com.example.footballgeeks.playersList.data.remote.PlayersListService
import com.example.footballgeeks.teamDetails.data.TeamDetailsRepository
import com.example.footballgeeks.teamDetails.data.remote.TeamDetailsRemoteDataSource
import com.example.footballgeeks.teamDetails.data.remote.TeamDetailsService
import com.example.footballgeeks.teamsList.data.TeamsListRepository
import com.example.footballgeeks.teamsList.data.remote.TeamsListRemoteDataSource
import com.example.footballgeeks.teamsList.data.remote.TeamsListService
import com.example.mycinema.common.data.remote.RetroFitClient
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FootballGeeksApp: Application() {

/*
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
    // ----
    private val teamDetailsService: TeamDetailsService by lazy {
        RetroFitClient.retrofit.create(TeamDetailsService::class.java)
    }

    private val teamDetailsRemoteDataSource: TeamDetailsRemoteDataSource by lazy {
        TeamDetailsRemoteDataSource(teamDetailsService)
    }
    val teamDetailsRepository: TeamDetailsRepository by lazy {
        TeamDetailsRepository(teamDetailsRemoteDataSource)
    }

    // --
    private val competitionsListService: CompetitionsListService by lazy {
        RetroFitClient.retrofit.create(CompetitionsListService::class.java)

    }
    private val competitionsListRemoteDataSource: CompetitionsListRemoteDataSource by lazy {
        CompetitionsListRemoteDataSource(competitionsListService)
    }
    val competitionsListRepository: CompetitionsListRepository by lazy {
        CompetitionsListRepository(competitionsListRemoteDataSource)
    }

    // --
    private val competitionDetailsService: CompetitionDetailsService by lazy {
        RetroFitClient.retrofit.create(CompetitionDetailsService::class.java)
    }
    private val competitionDetailsRemoteDataSource: CompetitionDetailsRemoteDataSource by lazy {
        CompetitionDetailsRemoteDataSource(competitionDetailsService)
    }
    val competitionDetailsRepository: CompetitionDetailsRepository by lazy {
        CompetitionDetailsRepository(competitionDetailsRemoteDataSource)
    }
*/
    // --
    private val playersListService: PlayersListService by lazy {
        RetroFitClient.retrofit.create(PlayersListService::class.java)
    }
    private val playersListRemoteDataSource: PlayersListRemoteDataSource by lazy {
        PlayersListRemoteDataSource(playersListService)
    }
    val playersListRepository: PlayersListRepository by lazy {
        PlayersListRepository(playersListRemoteDataSource)
    }

}