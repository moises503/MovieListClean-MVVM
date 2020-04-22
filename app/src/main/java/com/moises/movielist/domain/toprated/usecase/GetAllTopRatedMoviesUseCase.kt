package com.moises.movielist.domain.toprated.usecase

import com.moises.movielist.core.arch.NullParametersException
import com.moises.movielist.core.arch.UseCase
import com.moises.movielist.domain.toprated.model.TopRatedMovie
import com.moises.movielist.domain.toprated.repository.TopRatedMoviesRepository

class GetAllTopRatedMoviesUseCase(private val topratedMoviesRepository: TopRatedMoviesRepository) :
    UseCase<Unit, List<TopRatedMovie>>() {

    override suspend fun executeWithCoroutines(params: Unit?): List<TopRatedMovie> {
        params?.let {
            return topratedMoviesRepository.getAllTopRatedMovies()
        } ?: let {
            throw NullParametersException()
        }
    }
}