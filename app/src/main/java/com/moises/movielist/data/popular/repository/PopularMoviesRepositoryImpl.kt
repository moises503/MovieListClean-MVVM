package com.moises.movielist.data.popular.repository

import com.moises.movielist.data.popular.datasource.local.PopularMoviesLocalDataSource
import com.moises.movielist.data.popular.datasource.remote.PopularMoviesRemoteDataSource
import com.moises.movielist.domain.popular.exception.PopularMoviesNotFoundException
import com.moises.movielist.domain.popular.model.Movie
import com.moises.movielist.domain.popular.repository.PopularMoviesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class PopularMoviesRepositoryImpl(
    private val popularMoviesLocalDataSource: PopularMoviesLocalDataSource,
    private val popularMoviesRemoteDataSource: PopularMoviesRemoteDataSource
) : PopularMoviesRepository {

    override suspend fun getAllPopularMovies(): List<Movie> = withContext(IO) {

        val localPopularMovies = popularMoviesLocalDataSource.getAllPopularMoviesFromDatabase()

        val remotePopularMovies = try {
            popularMoviesRemoteDataSource.getAllPopularMoviesFromServer()
        } catch (ex: Exception) {
            when (ex) {
                is PopularMoviesNotFoundException -> throw ex
                else -> null
            }
        }

        if (remotePopularMovies != null) {
            popularMoviesLocalDataSource.savePopularMoviesToDatabase(remotePopularMovies)
        }

        remotePopularMovies ?: localPopularMovies
    }
}