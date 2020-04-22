package com.moises.movielist.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "toprated_movies")
data class TopRatedMoviesEntity(
    @PrimaryKey
    val id : Int,
    @ColumnInfo(name = "title")
    val title : String,
    @ColumnInfo(name = "overview")
    val overview : String,
    @ColumnInfo(name = "image")
    val image : String,
    @ColumnInfo(name = "popularity")
    val popularity : Double
)