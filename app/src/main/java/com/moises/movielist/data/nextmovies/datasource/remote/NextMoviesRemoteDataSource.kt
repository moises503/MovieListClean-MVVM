package com.moises.movielist.data.nextmovies.datasource.remote

import com.moises.movielist.domain.nextmovies.model.NextMovie

interface NextMoviesRemoteDataSource {
    suspend fun getAllNextMoviesFromServer() : List<NextMovie>
}