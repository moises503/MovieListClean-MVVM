package com.moises.movielist.data.nextmovies.repository
import com.moises.movielist.data.nextmovies.datasource.remote.NextMoviesRemoteDataSource
import com.moises.movielist.data.nextmovies.datasource.local.NextMoviesLocalDataSource
import com.moises.movielist.domain.nextmovies.exception.NextMoviesNotFoundException
import com.moises.movielist.domain.nextmovies.model.NextMovie
import com.moises.movielist.domain.nextmovies.repository.NextMoviesRepository



class NextMoviesRepositoryImpl(
    private val nextMoviesLocalDataSource: NextMoviesLocalDataSource,
    private val nextMoviesRemoteDataSource: NextMoviesRemoteDataSource
) : NextMoviesRepository {

    override suspend fun getAllNextMovies(): List<NextMovie> {

        val localNextMovies = nextMoviesLocalDataSource.getAllNextMoviesFromDatabase()

        val remoteNextMovies = try {
            nextMoviesRemoteDataSource.getAllNextMoviesFromServer()
        } catch (ex: Exception) {
            when (ex) {
                is NextMoviesNotFoundException -> throw ex
                else -> null
            }
        }

        if (remoteNextMovies != null) {
            nextMoviesLocalDataSource.saveNextMoviesToDatabase(remoteNextMovies)
        }

        return remoteNextMovies ?: localNextMovies
    }
}