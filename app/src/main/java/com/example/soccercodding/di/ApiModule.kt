package com.example.soccercodding.di

import com.example.soccercodding.data.SoccerCodingAPI
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

// Module for the API service
@Module
@InstallIn(ApplicationComponent::class)
object ApiModule {

    // having the base_url constant defined and initialized
    private const val BASE_URL = "https://storage.googleapis.com/"

    // Providing the interceptor for the whole life of the application
    @Singleton
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        // getting the call at body level
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Providing the OkHttpClient for the whole life of the application
    @Singleton
    @Provides
    fun provideOKHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        // Builder pattern and apply scope function to return the same object (client)
        OkHttpClient.Builder().apply {
            // adding the interceptor
            addInterceptor(loggingInterceptor)
            // building it
        }.build()

    // Providing the Retrofit instance for the whole life of the application
    @Singleton
    @Provides
    // taking the okHttpClient as parameter for the constructor
    fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder() // Builder pattern
            // setting the base url and the client for retrofit instance
        .baseUrl(BASE_URL).client(client)
            // adding coroutines as the adapter
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
            // Adding moshi as the converter
        .addConverterFactory(MoshiConverterFactory.create())

    // Providing the API instance by taking retrofit as parameter for the constructor for the whole life of the application
    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit.Builder): SoccerCodingAPI =
        retrofit.build().create(SoccerCodingAPI::class.java)
}