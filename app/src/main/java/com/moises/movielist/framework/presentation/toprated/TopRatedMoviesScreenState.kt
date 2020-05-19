package com.moises.movielist.framework.presentation.toprated

import com.moises.movielist.domain.toprated.model.TopRatedMovie

sealed class TopRatedMoviesScreenState {
    class Error(val message : String) : TopRatedMoviesScreenState()
    class Movies(val list : List<TopRatedMovie>) : TopRatedMoviesScreenState()
}