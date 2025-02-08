package com.example.footballgeeks.competitionDetails.data

import com.example.footballgeeks.common.remote.model.CompetitionsDetailsDTO
import com.example.footballgeeks.common.remote.model.CompetitionsDetailsStandings
import com.example.footballgeeks.common.remote.model.StatsPlayerDTO

interface CompetitionDetailsRepositoryInterface {
    suspend fun getCompetitionScorers(id: String, season: String): Result<StatsPlayerDTO>
    suspend fun getCompetitionStandings(id: String): Result<CompetitionsDetailsStandings>
    suspend fun getCompetitionDetails(code: String): Result<CompetitionsDetailsDTO>
}