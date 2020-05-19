package com.moises.movielist.framework.datasource.toprated.remote

import com.moises.movielist.data.popular.datasource.remote.PopularMoviesRemoteDataSource
import com.moises.movielist.data.toprated.datasource.remote.TopRatedMoviesRemoteDataSource
import com.moises.movielist.domain.popular.model.Movie
import com.moises.movielist.domain.toprated.model.TopRatedMovie
import com.moises.movielist.framework.datasource.popular.mapper.FromMovieItemToMovie
import com.moises.movielist.framework.datasource.toprated.mapper.FromTopRatedMovieItemToTopRatedMovie

class TopRatedMoviesRemoteDataSourceImpl(
    private val topratedMoviesEndPoint: TopRatedMoviesEndPoint,
    private val fromTopRatedMovieItemToTopRatedMovie: FromTopRatedMovieItemToTopRatedMovie
) : TopRatedMoviesRemoteDataSource {

    override suspend fun getAllTopRatedMoviesFromServer(): List<TopRatedMovie> {
        val topratedMoviesResponse =
            topratedMoviesEndPoint.retrievePopularMovies("d8ed12a5a59bf7c7ede638f054a03500", 1)
        return fromTopRatedMovieItemToTopRatedMovie.transformCollection(topratedMoviesResponse.results.orEmpty())
    }
}