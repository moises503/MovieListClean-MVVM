package com.moises.movielist.framework.datasource.popular.mapper

import com.moises.movielist.core.arch.Transformer
import com.moises.movielist.database.entities.PopularMoviesEntity
import com.moises.movielist.domain.popular.model.Movie

class FromMovieToPopularMoviesEntity : Transformer<Movie, PopularMoviesEntity>() {

    override fun transform(value: Movie): PopularMoviesEntity {
       return PopularMoviesEntity(
           id = value.id,
           title = value.title,
           overview = value.overview,
           image = value.image,
           popularity = value.popularity
       )
    }

    override fun reverseTransform(value: PopularMoviesEntity): Movie {
        return Movie(
            id = value.id,
            title = value.title,
            overview = value.overview,
            image = value.image,
            popularity = value.popularity
        )
    }
}