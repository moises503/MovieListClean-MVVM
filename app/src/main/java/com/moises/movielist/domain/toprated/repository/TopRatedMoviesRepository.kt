package com.moises.movielist.domain.toprated.repository


import com.moises.movielist.domain.toprated.model.TopRatedMovie

interface TopRatedMoviesRepository {
    suspend fun getAllTopRatedMovies() : List<TopRatedMovie>
}