package com.example.footballgeeks.common.remote.model

data class CompetitionsDTO(
    val competitions: List<CompetitionDetails>
)

data class CompetitionDetails(
    val id: Int,
    val area: Area,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String,
    val currentSeason: CurrentSeason
)

data class CurrentSeason(
    val id: Int,
    val startDate: String,
    val endDate: String,
)

// --

data class CompetitionsDetailsDTO(
    val area: Area,
    val id: Int,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String,
    val currentSeason: CurrentSeason,
    val seasons: List<Season>
)

data class Season(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val currentMatchday: Int,
    val winner: TeamDetails
)
