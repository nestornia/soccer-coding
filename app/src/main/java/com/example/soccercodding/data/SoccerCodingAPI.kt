package com.example.soccercodding.data

import com.example.soccercodding.model.SoccerData
import retrofit2.http.GET

// interface to interact with the web REST API service
interface SoccerCodingAPI {

    // getting the fixtures with retrofit @GET
    @GET("/cdn-og-test-api/test-task/fixtures.json")
    // emitting the result as a suspended List of items type SoccerData to make it through Coroutines
    suspend fun getFixtures(): List<SoccerData>

    // getting the results with retrofit @GET
    @GET("/cdn-og-test-api/test-task/results.json")
    // emitting the result as a suspended List of items type SoccerData to make it through Coroutines
    suspend fun getResults(): List<SoccerData>
}