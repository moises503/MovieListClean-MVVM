package com.moises.movielist.data.popular.datasource.remote

import com.moises.movielist.domain.popular.model.Movie

interface PopularMoviesRemoteDataSource {
    suspend fun getAllPopularMoviesFromServer() : List<Movie>
}