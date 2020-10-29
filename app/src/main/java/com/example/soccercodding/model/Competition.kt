package com.example.soccercodding.model

import com.squareup.moshi.JsonClass

// Moshi annotation to generate code at compile time to perform serialisation/deserialisation without using reflection
@JsonClass(generateAdapter = true)
data class Competition(
    val id: Int? = 0,
    val name: String? = ""
)