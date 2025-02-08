package com.example.footballgeeks.competitionsList.data

import com.example.footballgeeks.common.remote.model.CompetitionDetails

interface CompetitionListRepositoryInterface {
    suspend fun getCompetitions(): Result<List<CompetitionDetails>?>
}