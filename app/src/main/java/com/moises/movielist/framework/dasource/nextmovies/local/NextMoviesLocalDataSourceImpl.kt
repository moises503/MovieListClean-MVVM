package com.moises.movielist.framework.dasource.nextmovies.local

import com.moises.movielist.data.nextmovies.datasource.local.NextMoviesLocalDataSource
import com.moises.movielist.data.popular.datasource.local.PopularMoviesLocalDataSource
import com.moises.movielist.database.dao.NextMoviesDao
import com.moises.movielist.database.dao.PopularMoviesDao
import com.moises.movielist.domain.nextmovies.model.NextMovie
import com.moises.movielist.domain.popular.model.Movie
import com.moises.movielist.framework.dasource.nextmovies.mapper.FromNextMovieToNextMoviesEntity
import com.moises.movielist.framework.datasource.popular.mapper.FromMovieToPopularMoviesEntity

class NextMoviesLocalDataSourceImpl(
    private val fromNextMovieToNextMoviesEntity: FromNextMovieToNextMoviesEntity,
    private val nextMoviesDao: NextMoviesDao
) : NextMoviesLocalDataSource {

    override suspend fun getAllNextMoviesFromDatabase(): List<NextMovie> =
        fromNextMovieToNextMoviesEntity.reverseTransformCollection(nextMoviesDao.getSavedNextMovies())

    override suspend fun saveNextMoviesToDatabase(nextMovies: List<NextMovie>) =
        nextMoviesDao.saveNextMovies(
            fromNextMovieToNextMoviesEntity.transformCollection(
                nextMovies
            )
        )
}