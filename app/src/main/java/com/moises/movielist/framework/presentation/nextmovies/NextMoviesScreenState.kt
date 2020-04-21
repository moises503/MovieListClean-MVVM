package com.moises.movielist.framework.presentation.nextmovies

import com.moises.movielist.domain.nextmovies.model.NextMovie

sealed class NextMoviesScreenState {
    class Error(val message : String) : NextMoviesScreenState()
    class NextMovies(val list : List<NextMovie>) : NextMoviesScreenState()
}