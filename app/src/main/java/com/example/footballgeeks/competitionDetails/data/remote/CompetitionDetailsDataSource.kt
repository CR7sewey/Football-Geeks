package com.example.footballgeeks.competitionDetails.data.remote

import com.example.footballgeeks.common.remote.model.CompetitionsDetailsDTO
import com.example.footballgeeks.common.remote.model.CompetitionsDetailsStandings
import com.example.footballgeeks.common.remote.model.StatsPlayerDTO

interface CompetitionDetailsDataSource {
    suspend fun getCompetitionDetails(code: String): Result<CompetitionsDetailsDTO?>

    suspend fun getCompetitionStandings(id: String): Result<CompetitionsDetailsStandings?>

    suspend fun getCompetitionScorers(id: String, season: String): Result<StatsPlayerDTO?>
}