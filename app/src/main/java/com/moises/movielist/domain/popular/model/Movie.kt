package com.moises.movielist.domain.popular.model

data class Movie(
    val id : Int,
    val title : String,
    val overview : String,
    val image : String,
    val popularity : Double
)