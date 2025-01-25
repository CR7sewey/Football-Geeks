package com.example.footballgeeks.competitionDetails.data.remote

import com.example.footballgeeks.common.remote.model.CompetitionsDetailsDTO

interface CompetitionDetailsDataSource {
    suspend fun getCompetitionDetails(code: String): Result<CompetitionsDetailsDTO?>
}