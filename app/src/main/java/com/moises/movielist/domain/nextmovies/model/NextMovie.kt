package com.moises.movielist.domain.nextmovies.model

data class NextMovie(
    val id : Int,
    val title : String,
    val overview : String,
    val image : String,
    val popularity : Double
)