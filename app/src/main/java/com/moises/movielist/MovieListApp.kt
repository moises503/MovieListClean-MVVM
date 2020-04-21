package com.moises.movielist

import android.app.Application
import com.moises.movielist.core.arch.coreModule
import com.moises.movielist.framework.di.nextMoviesKoinModule
import com.moises.movielist.framework.di.popularMoviesKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieListApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MovieListApp)
            modules(arrayListOf(coreModule, popularMoviesKoinModule, nextMoviesKoinModule))
        }
    }
}