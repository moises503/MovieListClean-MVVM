package com.moises.movielist.framework.datasource.toprated.mapper

import com.moises.movielist.core.arch.Transformer
import com.moises.movielist.database.entities.PopularMoviesEntity
import com.moises.movielist.database.entities.TopRatedMoviesEntity
import com.moises.movielist.domain.popular.model.Movie
import com.moises.movielist.domain.toprated.model.TopRatedMovie

class FromTopRatedMovieToTopRatedMoviesEntity : Transformer<TopRatedMovie, TopRatedMoviesEntity>() {

    override fun transform(value: TopRatedMovie): TopRatedMoviesEntity {
       return TopRatedMoviesEntity(
           id = value.id,
           title = value.title,
           overview = value.overview,
           image = value.image,
           popularity = value.popularity
       )
    }

    override fun reverseTransform(value: TopRatedMoviesEntity): TopRatedMovie {
        return TopRatedMovie(
            id = value.id,
            title = value.title,
            overview = value.overview,
            image = value.image,
            popularity = value.popularity
        )
    }
}