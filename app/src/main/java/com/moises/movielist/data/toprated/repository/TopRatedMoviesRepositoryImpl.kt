package com.moises.movielist.data.toprated.repository

import com.moises.movielist.data.toprated.datasource.local.TopRatedMoviesLocalDataSource
import com.moises.movielist.data.toprated.datasource.remote.TopRatedMoviesRemoteDataSource
import com.moises.movielist.domain.toprated.exception.TopRatedMoviesNotFoundException
import com.moises.movielist.domain.toprated.model.TopRatedMovie
import com.moises.movielist.domain.toprated.repository.TopRatedMoviesRepository

class TopRatedMoviesRepositoryImpl(
    private val topratedMoviesLocalDataSource: TopRatedMoviesLocalDataSource,
    private val topratedMoviesRemoteDataSource: TopRatedMoviesRemoteDataSource
    ) : TopRatedMoviesRepository {

        override suspend fun getAllTopRatedMovies(): List<TopRatedMovie> {

            val localTopRatedMovies = topratedMoviesLocalDataSource.getAllTopRatedMoviesFromDatabase()

            val remoteTopRatedMovies = try {
                topratedMoviesRemoteDataSource.getAllTopRatedMoviesFromServer()
            } catch (ex: Exception) {
                when (ex) {
                    is TopRatedMoviesNotFoundException -> throw ex
                    else -> null
                }
            }

            if (remoteTopRatedMovies != null) {
                topratedMoviesLocalDataSource.saveTopRatedMoviesToDatabase(remoteTopRatedMovies)
            }

            return remoteTopRatedMovies ?: localTopRatedMovies
        }
    }