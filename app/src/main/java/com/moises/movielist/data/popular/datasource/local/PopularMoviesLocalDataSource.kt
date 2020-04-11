package com.moises.movielist.data.popular.datasource.local

import com.moises.movielist.domain.popular.model.Movie

interface PopularMoviesLocalDataSource {
    suspend fun getAllPopularMoviesFromDatabase() : List<Movie>
    suspend fun savePopularMoviesToDatabase(popularMovies : List<Movie>)
}