package com.moises.movielist.framework.dasource.nextmovies.remote

import com.moises.movielist.data.nextmovies.datasource.remote.NextMoviesRemoteDataSource
import com.moises.movielist.domain.nextmovies.model.NextMovie
import com.moises.movielist.framework.dasource.nextmovies.mapper.FromNextMovieItemToNextMovie

class NextMoviesRemoteDataSourceImpl(
    private val nextMoviesEndPoint: NextMoviesEndPoint,
    private val fromNextMovieItemToNextMovie: FromNextMovieItemToNextMovie
) : NextMoviesRemoteDataSource {

    override suspend fun getAllNextMoviesFromServer(): List<NextMovie> {
        val nextMoviesResponse =
            nextMoviesEndPoint.retrieveNextMovies("d8ed12a5a59bf7c7ede638f054a03500", 1)
        return fromNextMovieItemToNextMovie.transformCollection(nextMoviesResponse.results.orEmpty())
    }
}