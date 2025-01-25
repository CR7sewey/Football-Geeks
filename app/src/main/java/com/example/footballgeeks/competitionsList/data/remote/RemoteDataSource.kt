package com.example.footballgeeks.competitionsList.data.remote

import com.example.footballgeeks.common.remote.model.CompetitionDetails
import com.example.footballgeeks.common.remote.model.CompetitionsDTO

interface RemoteDataSource {

    suspend fun getCompetitions(): Result<List<CompetitionDetails>?>
}