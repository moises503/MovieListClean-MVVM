package com.moises.movielist.framework.di

import com.moises.movielist.data.nextmovies.datasource.local.NextMoviesLocalDataSource
import com.moises.movielist.data.nextmovies.datasource.remote.NextMoviesRemoteDataSource
import com.moises.movielist.data.nextmovies.repository.NextMoviesRepositoryImpl
import com.moises.movielist.database.MoviesDatabase
import com.moises.movielist.database.dao.NextMoviesDao
import com.moises.movielist.domain.nextmovies.repository.NextMoviesRepository
import com.moises.movielist.domain.nextmovies.usecase.GetAllNextMoviesUseCase
import com.moises.movielist.framework.dasource.nextmovies.local.NextMoviesLocalDataSourceImpl
import com.moises.movielist.framework.dasource.nextmovies.mapper.FromNextMovieItemToNextMovie
import com.moises.movielist.framework.dasource.nextmovies.mapper.FromNextMovieToNextMoviesEntity
import com.moises.movielist.framework.dasource.nextmovies.remote.NextMoviesEndPoint
import com.moises.movielist.framework.dasource.nextmovies.remote.NextMoviesRemoteDataSourceImpl
import com.moises.movielist.framework.presentation.nextmovies.NextMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


fun providesNextMoviesEndPoint(retrofit: Retrofit): NextMoviesEndPoint =
    retrofit.create(NextMoviesEndPoint::class.java)

fun providesNextMoviesDao(moviesDatabase: MoviesDatabase): NextMoviesDao =
    moviesDatabase.nextMoviesDao()

fun providesFromNextMovieItemToItem(): FromNextMovieItemToNextMovie = FromNextMovieItemToNextMovie()

fun providesNextMoviesRemoteDataSource(
    nextMoviesEndPoint: NextMoviesEndPoint,
    fromNextMovieItemToNextMovie: FromNextMovieItemToNextMovie
): NextMoviesRemoteDataSource =
    NextMoviesRemoteDataSourceImpl(nextMoviesEndPoint, fromNextMovieItemToNextMovie)

fun providesFromNextMovieToNextMoviesEntity(): FromNextMovieToNextMoviesEntity =
    FromNextMovieToNextMoviesEntity()

fun providesNextMoviesLocalDataSource(
    fromNextMovieToNextMoviesEntity: FromNextMovieToNextMoviesEntity,
    nextMoviesDao: NextMoviesDao
): NextMoviesLocalDataSource =
    NextMoviesLocalDataSourceImpl(fromNextMovieToNextMoviesEntity, nextMoviesDao)

fun providesNextMoviesRepository(
    nextMoviesLocalDataSource: NextMoviesLocalDataSource,
    nextMoviesRemoteDataSource: NextMoviesRemoteDataSource
): NextMoviesRepository =
    NextMoviesRepositoryImpl(nextMoviesLocalDataSource, nextMoviesRemoteDataSource)

fun providesGetAllNextMoviesUseCase(nextMoviesRepository: NextMoviesRepository) :
        GetAllNextMoviesUseCase = GetAllNextMoviesUseCase(nextMoviesRepository)


val nextMoviesKoinModule = module {
    single { providesNextMoviesEndPoint(get()) }
    single { providesNextMoviesDao(get()) }
    single { providesFromNextMovieItemToItem() }
    single { providesNextMoviesRemoteDataSource(get(), get()) }
    single { providesFromNextMovieToNextMoviesEntity() }
    single { providesNextMoviesLocalDataSource(get(), get())}
    single { providesNextMoviesRepository(get(), get()) }
    single { providesGetAllNextMoviesUseCase(get()) }
    viewModel { NextMoviesViewModel(get()) }
}