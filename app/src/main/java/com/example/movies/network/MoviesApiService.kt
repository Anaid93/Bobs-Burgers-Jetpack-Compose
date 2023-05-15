package com.example.movies.network

import com.example.movies.model.Movies
import retrofit2.http.GET

interface MoviesApiService {
    @GET("?limit=30&skip=0")
    suspend fun getMovies(): List<Movies>
}