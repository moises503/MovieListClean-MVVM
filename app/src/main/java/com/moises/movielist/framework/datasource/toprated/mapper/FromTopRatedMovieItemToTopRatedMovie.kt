package com.moises.movielist.framework.datasource.toprated.mapper

import com.moises.movielist.core.arch.Transformer
import com.moises.movielist.domain.toprated.model.TopRatedMovie
import com.moises.movielist.framework.datasource.toprated.remote.model.TopRatedMoviesItem

class FromTopRatedMovieItemToTopRatedMovie
    : Transformer<TopRatedMoviesItem, TopRatedMovie>() {
    override fun transform(value: TopRatedMoviesItem): TopRatedMovie {
        return TopRatedMovie(
            id = value.id,
            title =  value.title,
            overview =  value.overview.orEmpty(),
            image = value.posterPath.orEmpty(),
            popularity = value.popularity ?: 0.0
        )
    }
}