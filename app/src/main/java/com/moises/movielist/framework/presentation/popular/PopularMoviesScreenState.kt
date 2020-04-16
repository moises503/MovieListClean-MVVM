package com.moises.movielist.framework.presentation.popular

import com.moises.movielist.domain.popular.model.Movie

sealed class PopularMoviesScreenState {
    class Error(val message : String) : PopularMoviesScreenState()
    class Movies(val list : List<Movie>) : PopularMoviesScreenState()
}