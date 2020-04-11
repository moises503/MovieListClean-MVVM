package com.moises.movielist.framework.datasource.popular.local

import com.moises.movielist.data.popular.datasource.local.PopularMoviesLocalDataSource
import com.moises.movielist.database.dao.PopularMoviesDao
import com.moises.movielist.domain.popular.model.Movie
import com.moises.movielist.framework.datasource.popular.mapper.FromMovieToPopularMoviesEntity

class PopularMoviesLocalDataSourceImpl(
    private val fromMovieToPopularMoviesEntity: FromMovieToPopularMoviesEntity,
    private val popularMoviesDao: PopularMoviesDao
) : PopularMoviesLocalDataSource {

    override suspend fun getAllPopularMoviesFromDatabase(): List<Movie> =
        fromMovieToPopularMoviesEntity.reverseTransformCollection(popularMoviesDao.getSavedPopularMovies())

    override suspend fun savePopularMoviesToDatabase(popularMovies: List<Movie>) =
        popularMoviesDao.savePopularMovies(
            fromMovieToPopularMoviesEntity.transformCollection(
                popularMovies
            )
        )

}