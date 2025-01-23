package com.example.footballgeeks.teamsList.presentation.ui
import com.example.footballgeeks.common.remote.model.Team
import com.example.footballgeeks.common.remote.model.TeamDetails

data class TeamsListUIState(
    val list: List<TeamDetails> = emptyList<TeamDetails>(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = "Something went wrong",
)