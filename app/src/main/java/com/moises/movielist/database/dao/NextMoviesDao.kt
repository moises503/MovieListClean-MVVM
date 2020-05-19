package com.moises.movielist.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moises.movielist.database.entities.NextMoviesEntity
import com.moises.movielist.database.entities.PopularMoviesEntity

@Dao
interface NextMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNextMovies(nextMovies: List<NextMoviesEntity>)

    @Query("SELECT * FROM next_movies")
    suspend fun getSavedNextMovies(): List<NextMoviesEntity>
}