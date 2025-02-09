package com.example.landingpage.landingPage.remote.model

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

// --
data class CompetitionsDetailsStandings(
    val area: Area,
    val competition: CompetitionDetails,
    val season: Season,
    val standings: List<Standings>
)

data class Standings(
    val stage: String,
    val type: String,
    val group: String,
    val table: List<Table>
)

data class Table(
    val position: Int,
    val team: TeamDetails,
    val playedGames: Int,
    val won: Int,
    val draw: Int,
    val lost: Int,
    val points: Int,
    val goalsFor: Int,
    val goalsAgainst: Int
)