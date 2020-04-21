package com.moises.movielist.framework.dasource.nextmovies.mapper

import com.moises.movielist.core.arch.Transformer
import com.moises.movielist.database.entities.NextMoviesEntity
import com.moises.movielist.domain.nextmovies.model.NextMovie

class FromNextMovieToNextMoviesEntity : Transformer<NextMovie, NextMoviesEntity>() {

    override fun transform(value: NextMovie): NextMoviesEntity {
       return NextMoviesEntity(
           id = value.id,
           title = value.title,
           overview = value.overview,
           image = value.image,
           popularity = value.popularity
       )
    }

    override fun reverseTransform(value: NextMoviesEntity): NextMovie {
        return NextMovie(
            id = value.id,
            title = value.title,
            overview = value.overview,
            image = value.image,
            popularity = value.popularity
        )
    }
}