package com.example.soccercodding.data

import android.accounts.NetworkErrorException
import android.content.Context
import com.example.soccercodding.extensions.isConnected
import com.example.soccercodding.util.ApiState
import kotlinx.coroutines.flow.flow

// Building the repo that takes two params: context and the SoccerCodingAPI
class SoccerRepo constructor(
    private val context: Context,
    private val api: SoccerCodingAPI
) {
    // fun getFixtures is creating a flow from the given suspendable
    suspend fun getFixtures() = flow {
        // checking if the device has access to the internet
        if (context.isConnected) {
            // we will always do something so initial state will be loading
            emit(ApiState.Loading)
            // try-catch statement  to get the response
            try {
                // setting response to the api response of fixtures endpoint
                val response = api.getFixtures()
                // emit the corresponding flow state as successful
                emit(ApiState.Success(response))
            } catch (e: Exception) {
                // emit the corresponding flow state as error with the message
                emit(ApiState.Error(e))
            }
            // if not connected then return error message
        } else emit(ApiState.Error(NetworkErrorException("No Internet")))

    }

    // fun getResults is getting a flow from the given suspendable
    suspend fun getResults() = flow {
        // checking if the device has access to the internet
        if (context.isConnected) {
            // emit loading state
            emit(ApiState.Loading)
            // try-catch block for getting results response
            try {
                // creating and initializing response as results from the api
                val response = api.getResults()
                // emit the corresponding state of the flow as successful
                emit(ApiState.Success(response))
            } catch (e: Exception) {
                // emit the corresponding state of the flow as error if response is not catched successfully
                emit(ApiState.Error(e))
            }
            // emit the corresponding state of the flow as error if not connected
        } else emit(ApiState.Error(NetworkErrorException("No Internet")))
    }
}