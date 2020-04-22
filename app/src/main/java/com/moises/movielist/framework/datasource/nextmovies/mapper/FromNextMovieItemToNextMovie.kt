package com.moises.movielist.framework.dasource.nextmovies.mapper

import com.moises.movielist.core.arch.Transformer
import com.moises.movielist.domain.nextmovies.model.NextMovie
import com.moises.movielist.framework.dasource.nextmovies.remote.model.NextMoviesItem


class FromNextMovieItemToNextMovie : Transformer<NextMoviesItem, NextMovie>() {
    override fun transform(value: NextMoviesItem): NextMovie {
        return NextMovie(
            id = value.id,
            title =  value.title,
            overview =  value.overview.orEmpty(),
            image = value.posterPath.orEmpty(),
            popularity = value.popularity ?: 0.0
        )
    }
}