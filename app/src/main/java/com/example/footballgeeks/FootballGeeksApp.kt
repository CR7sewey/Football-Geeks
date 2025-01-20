package com.example.footballgeeks

import android.app.Application
import com.example.footballgeeks.landingPage.data.remote.LandingPageService
import com.example.mycinema.common.data.remote.RetroFitClient

class FootballGeeksApp: Application() {




    val landingPageService: LandingPageService by lazy {
        RetroFitClient.retrofit.create(LandingPageService::class.java)
    }
}