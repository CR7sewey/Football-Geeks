package com.example.footballgeeks.landingPage.presentation.ui

import com.example.footballgeeks.common.remote.model.Match

data class MatchesListUiState(
    val list: List<Match> = emptyList<Match>(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = "Something went wrong",
)