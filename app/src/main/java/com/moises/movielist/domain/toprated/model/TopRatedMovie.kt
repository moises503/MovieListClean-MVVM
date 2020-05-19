package com.moises.movielist.domain.toprated.model

data class TopRatedMovie(
    val id : Int,
    val title : String,
    val overview : String,
    val image : String,
    val popularity : Double
)