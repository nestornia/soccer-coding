package com.example.soccercodding.model

import com.squareup.moshi.JsonClass

// Moshi annotation to generate code at compile time to perform serialisation/deserialisation without using reflection
@JsonClass(generateAdapter = true)
data class AwayTeam(
    val id: Int? = 0,
    val name: String? = "",
    val shortName: String? = "",
    val abbr: String? = "",
    val alias: String? = ""
)