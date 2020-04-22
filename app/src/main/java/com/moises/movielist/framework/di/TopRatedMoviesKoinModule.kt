package com.moises.movielist.framework.di

import com.moises.movielist.data.toprated.datasource.local.TopRatedMoviesLocalDataSource
import com.moises.movielist.data.toprated.datasource.remote.TopRatedMoviesRemoteDataSource
import com.moises.movielist.data.toprated.repository.TopRatedMoviesRepositoryImpl
import com.moises.movielist.database.MoviesDatabase
import com.moises.movielist.database.dao.TopRatedMoviesDao
import com.moises.movielist.domain.toprated.repository.TopRatedMoviesRepository
import com.moises.movielist.domain.toprated.usecase.GetAllTopRatedMoviesUseCase
import com.moises.movielist.framework.datasource.toprated.local.TopRatedMoviesLocalDataSourceImpl
import com.moises.movielist.framework.datasource.toprated.mapper.FromTopRatedMovieItemToTopRatedMovie
import com.moises.movielist.framework.datasource.toprated.mapper.FromTopRatedMovieToTopRatedMoviesEntity
import com.moises.movielist.framework.datasource.toprated.remote.TopRatedMoviesEndPoint
import com.moises.movielist.framework.datasource.toprated.remote.TopRatedMoviesRemoteDataSourceImpl
import com.moises.movielist.framework.presentation.toprated.TopRatedMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


fun providesTopRatedMoviesEndPoint(retrofit: Retrofit): TopRatedMoviesEndPoint =
    retrofit.create(TopRatedMoviesEndPoint::class.java)

fun providesTopRatedMoviesDao(moviesDatabase: MoviesDatabase): TopRatedMoviesDao =
    moviesDatabase.topratedMoviesDao()

fun providesFromTopRatedMovieItemToItem(): FromTopRatedMovieItemToTopRatedMovie = FromTopRatedMovieItemToTopRatedMovie()

fun providesTopRatedMoviesRemoteDataSource(
    topratedMoviesEndPoint: TopRatedMoviesEndPoint,
    fromTopRatedMovieItemToTopRatedMovie: FromTopRatedMovieItemToTopRatedMovie
): TopRatedMoviesRemoteDataSource =
    TopRatedMoviesRemoteDataSourceImpl(topratedMoviesEndPoint, fromTopRatedMovieItemToTopRatedMovie)

fun providesFromTopRatedMovieToTopRatedMoviesEntity(): FromTopRatedMovieToTopRatedMoviesEntity =
    FromTopRatedMovieToTopRatedMoviesEntity()

fun providesTopRatedMoviesLocalDataSource(
    fromTopRatedMovieToTopRatedMoviesEntity: FromTopRatedMovieToTopRatedMoviesEntity,
    topratedMoviesDao: TopRatedMoviesDao
): TopRatedMoviesLocalDataSource =
    TopRatedMoviesLocalDataSourceImpl(fromTopRatedMovieToTopRatedMoviesEntity, topratedMoviesDao)

fun providesTopRatedMoviesRepository(
    topratedMoviesLocalDataSource: TopRatedMoviesLocalDataSource,
    topratedMoviesRemoteDataSource: TopRatedMoviesRemoteDataSource
): TopRatedMoviesRepository =
    TopRatedMoviesRepositoryImpl(topratedMoviesLocalDataSource, topratedMoviesRemoteDataSource)

fun providesGetAllTopRatedMoviesUseCase(topratedMoviesRepository: TopRatedMoviesRepository) :
        GetAllTopRatedMoviesUseCase = GetAllTopRatedMoviesUseCase(topratedMoviesRepository)


val topratedMoviesKoinModule = module {
    single { providesTopRatedMoviesEndPoint(get()) }
    single { providesTopRatedMoviesDao(get()) }
    single { providesFromTopRatedMovieItemToItem() }
    single { providesTopRatedMoviesRemoteDataSource(get(), get()) }
    single { providesFromTopRatedMovieToTopRatedMoviesEntity() }
    single { providesTopRatedMoviesLocalDataSource(get(), get())}
    single { providesTopRatedMoviesRepository(get(), get()) }
    single { providesGetAllTopRatedMoviesUseCase(get()) }
    viewModel { TopRatedMoviesViewModel(get()) }
}