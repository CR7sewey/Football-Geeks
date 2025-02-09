package com.example.landingpage.landingPage.remote.model

import okhttp3.internal.http2.ErrorCode

data class MatchesDTO(
    val matches: List<Match>
)

data class Match(
    val area: Area,
    val competition: Competition,
    val id: Int,
    val status: String,
    val minute: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val score: Score

)

data class Area(
    val id: Int,
    val name: String,
    val flag: String
)

data class Competition(
    val id: Int,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String
)

data class Team(
    val id: Int,
    val name: String,
    val shortName: String,
    val crest: String,
    val leagueRank: Int,
)

data class Score(
    val winner: String,
    val fullTime: ScoreGoals,
    val halfTime: ScoreGoals,
)

data class GoalsTime(
    val minute: Int,
    val score: ScoreGoals
)

data class ScoreGoals(
    val home: Int,
    val away: Int
)


/**
{
"area": {
"id": 2045,
"name": "Chile",
"code": "CHL",
"flag": null
},
"competition": {
"id": 2048,
"name": "Primera División",
"code": "CPD",
"type": "LEAGUE",
"emblem": null
},
"season": {
"id": 1102,
"startDate": "2022-02-06",
"endDate": "2022-11-30",
"currentMatchday": 13,
"winner": null,
"stages": [
"REGULAR_SEASON"
]
},
"id": 392181,
"utcDate": "2022-05-17T00:30:00Z",
"status": "FINISHED",
"minute": "90",
"injuryTime": 5,
"attendance": null,
"venue": null,
"matchday": 13,
"stage": "REGULAR_SEASON",
"group": null,
"lastUpdated": "2022-05-17T17:49:28Z",
"homeTeam": {
"id": 4465,
"name": "CDP Curicó Unido",
"shortName": "Curicó",
"tla": "CUR",
"crest": "https://crests.football-data.org/4465.svg",
"coach": {
"id": 104512,
"name": "Damian Munoz",
"nationality": "Chile"
},
"leagueRank": 6,
"formation": "4-3-3",
"lineup": [
{
"id": 111099,
"name": "Fabián Cerda",
"position": "Goalkeeper",
"shirtNumber": 12
},
{
"id": 46409,
"name": "Matías Cahais",
"position": "Centre-Back",
"shirtNumber": 3
},
{
"id": 23433,
"name": "Yerko Leiva",
"position": "Attacking Midfield",
"shirtNumber": 10
},
{
"id": 28306,
"name": "Agustín Nadruz",
"position": "Defensive Midfield",
"shirtNumber": 5
},
{
"id": 123230,
"name": "Mario Sandoval",
"position": "Central Midfield",
"shirtNumber": 8
},
{
"id": 23602,
"name": "Ronald De La Fuente",
"position": "Left-Back",
"shirtNumber": 19
},
{
"id": 23467,
"name": "Franco Bechtholdt",
"position": "Centre-Back",
"shirtNumber": 16
},
{
"id": 17210,
"name": "Juan Pablo Gómez",
"position": "Right-Back",
"shirtNumber": 15
},
{
"id": 138242,
"name": "Bayron Oyarzo",
"position": "Right Winger",
"shirtNumber": 11
},
{
"id": 59028,
"name": "Rodrigo Holgado",
"position": "Centre-Forward",
"shirtNumber": 9
},
{
"id": 28427,
"name": "Diego Coelho",
"position": "Centre-Forward",
"shirtNumber": 27
}
],
"bench": [
{
"id": 129935,
"name": "Tomás Vergara",
"position": null,
"shirtNumber": 1
},
{
"id": 23758,
"name": "José Rojas",
"position": "Centre-Back",
"shirtNumber": 13
},
{
"id": 23466,
"name": "Diego Urzúa",
"position": "Central Midfield",
"shirtNumber": 6
},
{
"id": 23627,
"name": "Felipe Fritz",
"position": "Left Winger",
"shirtNumber": 7
},
{
"id": 60772,
"name": "Federico Castro",
"position": "Left Winger",
"shirtNumber": 20
},
{
"id": 168037,
"name": "Joaquin Gonzalez",
"position": "Right Winger",
"shirtNumber": 14
},
{
"id": 167922,
"name": "Martin Miguel Cortes",
"position": null,
"shirtNumber": 17
}
]
},
"awayTeam": {
"id": 4455,
"name": "Audax CS Italiano",
"shortName": "Audax Italiano",
"tla": "AUD",
"crest": "https://crests.football-data.org/4455.svg",
"coach": {
"id": 23574,
"name": "Juan Ribera",
"nationality": "Chile"
},
"leagueRank": 12,
"formation": "3-5-2",
"lineup": [
{
"id": 23817,
"name": "Álvaro Salazar",
"position": "Goalkeeper",
"shirtNumber": 13
},
{
"id": 23545,
"name": "Fabián Torres",
"position": "Centre-Back",
"shirtNumber": 5
},
{
"id": 23408,
"name": "Pablo Alvarado",
"position": "Centre-Back",
"shirtNumber": 32
},
{
"id": 23530,
"name": "Roberto Cereceda",
"position": "Left-Back",
"shirtNumber": 28
},
{
"id": 23524,
"name": "Fernando Cornejo",
"position": "Central Midfield",
"shirtNumber": 8
},
{
"id": 23694,
"name": "Matías Sepúlveda",
"position": "Left Midfield",
"shirtNumber": 17
},
{
"id": 23522,
"name": "Osvaldo Bosso",
"position": "Right-Back",
"shirtNumber": 4
},
{
"id": 1130,
"name": "Tomás Andrade",
"position": "Attacking Midfield",
"shirtNumber": 35
},
{
"id": 23557,
"name": "Bryan Figueroa",
"position": "Right Winger",
"shirtNumber": 19
},
{
"id": 23843,
"name": "Luis Riveros",
"position": "Right Winger",
"shirtNumber": 11
},
{
"id": 168002,
"name": "Nikolas Aedo",
"position": null,
"shirtNumber": 20
}
],
"bench": [
{
"id": 118809,
"name": "Tomás Ahumada",
"position": "Goalkeeper",
"shirtNumber": 12
},
{
"id": 23553,
"name": "Diego Torres",
"position": "Left-Back",
"shirtNumber": 6
},
{
"id": 23543,
"name": "Nicolás Fernández",
"position": "Right-Back",
"shirtNumber": 7
},
{
"id": 140227,
"name": "Alfred Canales",
"position": "Defensive Midfield",
"shirtNumber": 22
},
{
"id": 179039,
"name": "Marlon Carrasco",
"position": "Midfield",
"shirtNumber": 23
},
{
"id": 23667,
"name": "Michael Fuentes",
"position": "Left Winger",
"shirtNumber": 27
},
{
"id": 23826,
"name": "German Estigarribia",
"position": "Centre-Forward",
"shirtNumber": 21
}
]
},
"score": {
"winner": "HOME_TEAM",
"duration": "REGULAR",
"fullTime": {
"home": 4,
"away": 1
},
"halfTime": {
"home": 2,
"away": 1
}
},
"goals": [
{
"minute": 14,
"injuryTime": null,
"type": "REGULAR",
"team": {
"id": 4465,
"name": "CDP Curicó Unido"
},
"scorer": {
"id": 59028,
"name": "Rodrigo Holgado"
},
"assist": null,
"score": {
"home": 1,
"away": 0
}
},
{
"minute": 21,
"injuryTime": null,
"type": "REGULAR",
"team": {
"id": 4465,
"name": "CDP Curicó Unido"
},
"scorer": {
"id": 138242,
"name": "Bayron Oyarzo"
},
"assist": null,
"score": {
"home": 2,
"away": 0
}
},
{
"minute": 38,
"injuryTime": null,
"type": "PENALTY",
"team": {
"id": 4455,
"name": "Audax CS Italiano"
},
"scorer": {
"id": 1130,
"name": "Tomás Andrade"
},
"assist": null,
"score": {
"home": 2,
"away": 1
}
},
{
"minute": 62,
"injuryTime": null,
"type": "REGULAR",
"team": {
"id": 4465,
"name": "CDP Curicó Unido"
},
"scorer": {
"id": 138242,
"name": "Bayron Oyarzo"
},
"assist": null,
"score": {
"home": 3,
"away": 1
}
},
{
"minute": 80,
"injuryTime": null,
"type": "REGULAR",
"team": {
"id": 4465,
"name": "CDP Curicó Unido"
},
"scorer": {
"id": 28306,
"name": "Agustín Nadruz"
},
"assist": null,
"score": {
"home": 4,
"away": 1
}
}
],
"penalties": [
{
"player": {
"id": 1130,
"name": "Tomás Andrade"
},
"team": {
"id": 4455,
"name": "Audax CS Italiano"
},
"scored": true
},
{
"player": {
"id": 1130,
"name": "Tomás Andrade"
},
"team": {
"id": 4455,
"name": "Audax CS Italiano"
},
"scored": true
}
],
"bookings": [],
"substitutions": [],
"odds": {
"homeWin": null,
"draw": null,
"awayWin": null
},
"referees": []
},

 */