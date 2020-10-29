package com.example.soccercodding.model

import com.squareup.moshi.JsonClass

// Moshi annotation to generate code at compile time to perform serialisation/deserialisation without using reflection
@JsonClass(generateAdapter = true)
data class SoccerData(
    val id: Int = 0,
    val type: String,
    // home team returns a HomeTeam object
    val homeTeam: HomeTeam? = HomeTeam(),
    // away team returns a AwayTeam object
    val awayTeam: AwayTeam? = AwayTeam(),
    val date: String? = "",
    // competitionStage returns a competitionStage object
    val competitionStage: CompetitionStage? = null,
    // venue returns a venue object
    val venue: Venue? = null,
    val state: String = "",
    // score returns a score object
    val score: Score? = null
)