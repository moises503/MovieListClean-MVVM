package com.moises.movielist.domain.nextmovies.repository

import com.moises.movielist.domain.popular.model.Movie

interface NextMoviesRepository {
    fun getAllNextMovies() : List<Movie>
}