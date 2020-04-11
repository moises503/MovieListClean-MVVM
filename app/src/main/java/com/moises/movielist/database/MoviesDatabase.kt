package com.moises.movielist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moises.movielist.database.entities.PopularMoviesEntity

@Database(entities = [PopularMoviesEntity::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    companion object {
        fun create(context: Context) : MoviesDatabase {
            return Room.databaseBuilder(
                context,
                MoviesDatabase::class.java,
                "movies"
            ).build()
        }
    }
}