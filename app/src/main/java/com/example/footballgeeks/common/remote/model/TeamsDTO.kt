package com.example.footballgeeks.common.remote.model

data class TeamsDTO(
    val teams: List<TeamDetails>
)

data class TeamDetails(
    val id: Int,
    val name: String,
    val shortName: String,
    val tla: String,
    val crest: String,
    val address: String,
    val website: String,
    val founded: Int,
    val venue: String,
    val lastUpdated: String
)