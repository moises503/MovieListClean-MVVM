package com.moises.movielist.framework.datasource.popular.mapper

import com.moises.movielist.core.arch.Transformer
import com.moises.movielist.domain.popular.model.Movie
import com.moises.movielist.framework.datasource.popular.remote.model.MoviesItem

class FromMovieItemToMovie : Transformer<MoviesItem, Movie>() {
    override fun transform(value: MoviesItem): Movie {
        return Movie(
            id = value.id,
            title =  value.title,
            overview =  value.overview.orEmpty(),
            image = value.posterPath.orEmpty(),
            popularity = value.popularity ?: 0.0
        )
    }
}