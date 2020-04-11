package com.moises.movielist.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moises.movielist.database.entities.PopularMoviesEntity

@Dao
interface PopularMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePopularMovies(popularMovies: List<PopularMoviesEntity>)

    @Query("SELECT * FROM popular_movies")
    suspend fun getSavedPopularMovies(): List<PopularMoviesEntity>
}