package com.moises.movielist.data.toprated.datasource.local

import com.moises.movielist.domain.toprated.model.TopRatedMovie


interface TopRatedMoviesLocalDataSource {
    suspend fun getAllTopRatedMoviesFromDatabase() : List<TopRatedMovie>
    suspend fun saveTopRatedMoviesToDatabase(topratedMovies : List<TopRatedMovie>)
}