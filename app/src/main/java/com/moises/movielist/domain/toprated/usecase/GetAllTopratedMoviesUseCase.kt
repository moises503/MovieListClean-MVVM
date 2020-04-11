package com.moises.movielist.domain.toprated.usecase

import com.moises.movielist.core.arch.NullParametersException
import com.moises.movielist.core.arch.UseCase
import com.moises.movielist.domain.popular.model.Movie
import com.moises.movielist.domain.toprated.repository.TopratedMoviesRepository

class GetAllTopratedMoviesUseCase(private val topratedMoviesRepository: TopratedMoviesRepository) :
    UseCase<Unit, List<Movie>>() {

    override fun execute(params: Unit?): List<Movie> {
        params?.let {
            return topratedMoviesRepository.getAllTopratedMovies()
        } ?: let {
            throw NullParametersException()
        }
    }
}