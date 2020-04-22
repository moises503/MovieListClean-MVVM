package com.moises.movielist.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moises.movielist.database.entities.TopRatedMoviesEntity

@Dao
interface TopRatedMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTopRatedMovies(topratedMovies: List<TopRatedMoviesEntity>)

    @Query("SELECT * FROM toprated_movies")
    suspend fun getSavedTopRatedMovies(): List<TopRatedMoviesEntity>
}