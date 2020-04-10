package com.moises.movielist.domain.popular.repository

import com.moises.movielist.domain.popular.model.Movie

interface PopularMoviesRepository {
    fun getAllPopularMovies() : List<Movie>
}