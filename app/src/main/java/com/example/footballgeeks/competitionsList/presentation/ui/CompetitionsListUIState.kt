package com.example.footballgeeks.competitionsList.presentation.ui

import com.example.footballgeeks.common.remote.model.CompetitionDetails


data class CompetitionsListUIState(
    val list: List<CompetitionDetails> = emptyList<CompetitionDetails>(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = "Something went wrong",
)