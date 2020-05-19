package com.moises.movielist.framework.datasource.toprated.local

import com.moises.movielist.data.toprated.datasource.local.TopRatedMoviesLocalDataSource
import com.moises.movielist.database.dao.TopRatedMoviesDao
import com.moises.movielist.domain.toprated.model.TopRatedMovie
import com.moises.movielist.framework.datasource.toprated.mapper.FromTopRatedMovieToTopRatedMoviesEntity

class TopRatedMoviesLocalDataSourceImpl(
    private val fromTopRatedMovieToTopRatedMoviesEntity: FromTopRatedMovieToTopRatedMoviesEntity,
    private val topratedMoviesDao: TopRatedMoviesDao
) : TopRatedMoviesLocalDataSource {

   override suspend fun getAllTopRatedMoviesFromDatabase(): List<TopRatedMovie> =
        fromTopRatedMovieToTopRatedMoviesEntity.reverseTransformCollection(topratedMoviesDao.getSavedTopRatedMovies())

    override suspend fun saveTopRatedMoviesToDatabase(topratedMovies: List<TopRatedMovie>) =
        topratedMoviesDao.saveTopRatedMovies(
            fromTopRatedMovieToTopRatedMoviesEntity.transformCollection(
                topratedMovies
            )
        )

}