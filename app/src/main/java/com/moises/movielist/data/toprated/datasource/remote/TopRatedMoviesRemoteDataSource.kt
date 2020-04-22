package com.moises.movielist.data.toprated.datasource.remote

import com.moises.movielist.domain.toprated.model.TopRatedMovie

interface TopRatedMoviesRemoteDataSource {
    suspend fun getAllTopRatedMoviesFromServer() : List<TopRatedMovie>
}