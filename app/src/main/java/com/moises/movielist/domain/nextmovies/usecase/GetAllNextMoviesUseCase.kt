package com.moises.movielist.domain.nextmovies.usecase

import com.moises.movielist.core.arch.NullParametersException
import com.moises.movielist.core.arch.UseCase
import com.moises.movielist.domain.nextmovies.model.NextMovie
import com.moises.movielist.domain.nextmovies.repository.NextMoviesRepository



class GetAllNextMoviesUseCase(private val nextMoviesRepository: NextMoviesRepository) :
    UseCase<Unit, List<NextMovie>>() {

    override suspend fun executeWithCoroutines(params: Unit?): List<NextMovie> {
        params?.let {
            return nextMoviesRepository.getAllNextMovies()
        } ?: let {
            throw NullParametersException()
        }
    }
}
