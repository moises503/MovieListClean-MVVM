package com.moises.movielist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moises.movielist.database.dao.NextMoviesDao
import com.moises.movielist.database.dao.PopularMoviesDao
import com.moises.movielist.database.entities.NextMoviesEntity
import com.moises.movielist.database.entities.PopularMoviesEntity

@Database(entities = [PopularMoviesEntity::class, NextMoviesEntity::class], version = 2)
abstract class MoviesDatabase : RoomDatabase() {
    companion object {
        fun create(context: Context) : MoviesDatabase {
            return Room.databaseBuilder(
                context,
                MoviesDatabase::class.java,
                "movies2"
            ).build()
        }
    }

    abstract fun popularMoviesDao() : PopularMoviesDao
    abstract fun nextMoviesDao():NextMoviesDao
}