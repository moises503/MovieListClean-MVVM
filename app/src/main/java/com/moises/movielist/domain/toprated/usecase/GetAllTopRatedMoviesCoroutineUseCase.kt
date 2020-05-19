package com.moises.movielist.domain.toprated.usecase

import com.moises.movielist.core.arch.NullParametersException
import com.moises.movielist.core.arch.CoroutineUseCase
import com.moises.movielist.domain.toprated.model.TopRatedMovie
import com.moises.movielist.domain.toprated.repository.TopRatedMoviesRepository

class GetAllTopRatedMoviesCoroutineUseCase(private val topratedMoviesRepository: TopRatedMoviesRepository) :
    CoroutineUseCase<Unit, List<TopRatedMovie>>() {

    override suspend fun execute(params: Unit?): List<TopRatedMovie> {
        params?.let {
            return topratedMoviesRepository.getAllTopRatedMovies()
        } ?: throw NullParametersException()
    }
}