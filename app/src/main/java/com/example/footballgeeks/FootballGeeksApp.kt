package com.example.footballgeeks

import android.app.Application
import com.example.footballgeeks.landingPage.data.LandingPageRepository
import com.example.footballgeeks.landingPage.data.remote.LandingPageRemoteDataSource
import com.example.footballgeeks.landingPage.data.remote.LandingPageService
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
}