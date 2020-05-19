package com.moises.movielist.domain.nextmovies.repository

import com.moises.movielist.domain.nextmovies.model.NextMovie

interface NextMoviesRepository {
    suspend fun getAllNextMovies() : List<NextMovie>
}

