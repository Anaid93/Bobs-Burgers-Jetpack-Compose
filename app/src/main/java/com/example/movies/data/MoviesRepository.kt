package com.example.movies.data

import com.example.movies.model.Movies
import com.example.movies.network.MoviesApiService


interface MoviesRepository{
    suspend fun getMovies(): List<Movies>
}

class DefaultMoviesRepository(
    private val moviesApiService: MoviesApiService
) : MoviesRepository{
    override suspend fun getMovies(): List<Movies> = moviesApiService.getMovies()
}