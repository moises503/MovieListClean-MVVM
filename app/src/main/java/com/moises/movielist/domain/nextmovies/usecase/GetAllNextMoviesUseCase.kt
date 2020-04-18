package com.moises.movielist.domain.nextmovies.usecase

import com.moises.movielist.core.arch.NullParametersException
import com.moises.movielist.core.arch.UseCase
import com.moises.movielist.domain.nextmovies.repository.NextMoviesRepository
import com.moises.movielist.domain.popular.model.Movie


abstract  class GetAllNextMoviesUseCase(private val nextMoviesRepository: NextMoviesRepository) :
    UseCase<Unit, List<Movie>>() {

    fun execute(params: Unit?): List<Movie> {
        params?.let {
            return nextMoviesRepository.getAllNextMovies()
        } ?: let {
            throw NullParametersException()
        }
    }
}