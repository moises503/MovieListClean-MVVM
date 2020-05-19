package com.moises.movielist.domain.popular.usecase

import com.moises.movielist.core.arch.NullParametersException
import com.moises.movielist.core.arch.CoroutineUseCase
import com.moises.movielist.domain.popular.model.Movie
import com.moises.movielist.domain.popular.repository.PopularMoviesRepository

class GetAllPopularMoviesCoroutineUseCase(private val popularMoviesRepository: PopularMoviesRepository) :
    CoroutineUseCase<Unit, List<Movie>>() {

    override suspend fun execute(params: Unit?): List<Movie> {
        params?.let {
            return popularMoviesRepository.getAllPopularMovies()
        } ?: throw NullParametersException()
    }
}