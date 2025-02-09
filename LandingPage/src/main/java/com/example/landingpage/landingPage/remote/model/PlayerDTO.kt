package com.example.landingpage.landingPage.remote.model

data class StatsPlayerDTO(
    val competition: CompetitionDetails,
    val season: Season,
    val scorers: List<Scorers>
)

data class Scorers(
    val player: PlayerDTO,
    val team: TeamDetails,
    val playedMatches: Int,
    val goals: Int,
    val assists: Int,
    val penalties: Int
)

data class PlayerDTO(
    val id: Int,
    val name: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val nationality: String,
    val section: String,
    val position: String,
    val shirtNumber: Int,
)