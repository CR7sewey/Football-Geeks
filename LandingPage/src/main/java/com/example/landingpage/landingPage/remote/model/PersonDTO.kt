package com.example.landingpage.landingPage.remote.model

data class PersonDTO(
    val id: String,
    val name: String,
    val firstName: String,
    val lastName: String,
    val dateOfBirth: String,
    val nationality: String,
    val section: String,
    val position: String,
    val shirtNumber: Int,
    val lastUpdated: String,
    val currentTeam: TeamDetails
)
