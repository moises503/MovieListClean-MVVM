package com.moises.movielist.data.nextmovies.datasource.local

import com.moises.movielist.domain.nextmovies.model.NextMovie

interface NextMoviesLocalDataSource {
    suspend fun getAllNextMoviesFromDatabase() : List<NextMovie>
    suspend fun saveNextMoviesToDatabase(nextMovies : List<NextMovie>)
}