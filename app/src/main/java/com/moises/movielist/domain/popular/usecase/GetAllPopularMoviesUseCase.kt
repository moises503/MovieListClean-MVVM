package com.moises.movielist.domain.popular.usecase

import com.moises.movielist.core.arch.NullParametersException
import com.moises.movielist.core.arch.UseCase
import com.moises.movielist.domain.popular.model.Movie
import com.moises.movielist.domain.popular.repository.PopularMoviesRepository

class GetAllPopularMoviesUseCase(private val popularMoviesRepository: PopularMoviesRepository) :
    UseCase<Unit, List<Movie>>() {

    override suspend fun executeWithCoroutines(params: Unit?): List<Movie> {
        params?.let {
            return popularMoviesRepository.getAllPopularMovies()
        } ?: let {
            throw NullParametersException()
        }
    }
}