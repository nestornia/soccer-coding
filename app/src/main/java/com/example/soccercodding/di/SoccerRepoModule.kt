package com.example.soccercodding.di

import android.content.Context
import com.example.soccercodding.data.SoccerCodingAPI
import com.example.soccercodding.data.SoccerRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

// Module for the Repository in the application component
@Module
@InstallIn(ApplicationComponent::class)
object SoccerRepoModule {

    // Providing the Repo implementation for the whole life of the application
    @Singleton
    @Provides
    fun provideSoccerRepo(
        // using annotation to get the application context as param 1
        @ApplicationContext context: Context,
        // using api class as param 2
        api: SoccerCodingAPI
    // returning the SoccerRepo object and initializing it
    ): SoccerRepo = SoccerRepo(context, api)
}