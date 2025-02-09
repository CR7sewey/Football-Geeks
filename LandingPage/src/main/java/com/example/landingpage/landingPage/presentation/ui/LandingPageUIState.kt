package com.example.landingpage.landingPage.presentation.ui

import com.example.landingpage.landingPage.remote.model.Match


data class MatchesListUiState(
    val list: List<Match> = emptyList<Match>(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = "Something went wrong",
)