package com.moises.movielist.domain.nextmovies.usecase

import com.moises.movielist.core.arch.NullParametersException
import com.moises.movielist.core.arch.CoroutineUseCase
import com.moises.movielist.domain.nextmovies.model.NextMovie
import com.moises.movielist.domain.nextmovies.repository.NextMoviesRepository



class GetAllNextMoviesCoroutineUseCase(private val nextMoviesRepository: NextMoviesRepository) :
    CoroutineUseCase<Unit, List<NextMovie>>() {

    override suspend fun execute(params: Unit?): List<NextMovie> {
        params?.let {
            return nextMoviesRepository.getAllNextMovies()
        } ?: throw NullParametersException()
    }
}
