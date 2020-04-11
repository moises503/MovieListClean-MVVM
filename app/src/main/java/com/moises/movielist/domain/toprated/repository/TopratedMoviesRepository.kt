package com.moises.movielist.domain.toprated.repository

import com.moises.movielist.domain.popular.model.Movie

interface TopratedMoviesRepository {
    fun getAllTopratedMovies() : List<Movie>
}