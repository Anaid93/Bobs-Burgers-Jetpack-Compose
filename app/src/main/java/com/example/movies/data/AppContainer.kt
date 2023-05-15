package com.example.movies.data

import com.example.movies.network.MoviesApiService
import retrofit2.Retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType


interface AppContainer{
    val moviesRepository: MoviesRepository
}

class DefaultAppContainer : AppContainer{
    private val BASE_URL =
        "https://bobsburgers-api.herokuapp.com/characters/"


    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json{ignoreUnknownKeys = true}.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }

    override val moviesRepository: MoviesRepository by lazy {
        DefaultMoviesRepository(retrofitService)
    }
}