package com.moises.movielist.domain.popular.repository

import com.moises.movielist.domain.popular.model.Movie

interface PopularMoviesRepository {
    suspend fun getAllPopularMovies() : List<Movie>
}