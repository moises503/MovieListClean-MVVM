package com.moises.movielist.framework.datasource.popular.remote

import com.moises.movielist.data.popular.datasource.remote.PopularMoviesRemoteDataSource
import com.moises.movielist.domain.popular.model.Movie
import com.moises.movielist.framework.datasource.popular.mapper.FromMovieItemToMovie

class PopularMoviesRemoteDataSourceImpl(
    private val popularMoviesEndPoint: PopularMoviesEndPoint,
    private val fromMovieItemToMovie: FromMovieItemToMovie
) : PopularMoviesRemoteDataSource {

    override suspend fun getAllPopularMoviesFromServer(): List<Movie> {
        val popularMoviesResponse =
            popularMoviesEndPoint.retrievePopularMovies("d8ed12a5a59bf7c7ede638f054a03500", 1)
        return fromMovieItemToMovie.transformCollection(popularMoviesResponse.results.orEmpty())
    }
}