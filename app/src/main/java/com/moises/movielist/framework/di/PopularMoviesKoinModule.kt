package com.moises.movielist.framework.di

import com.moises.movielist.data.popular.datasource.local.PopularMoviesLocalDataSource
import com.moises.movielist.data.popular.datasource.remote.PopularMoviesRemoteDataSource
import com.moises.movielist.data.popular.repository.PopularMoviesRepositoryImpl
import com.moises.movielist.database.MoviesDatabase
import com.moises.movielist.database.dao.PopularMoviesDao
import com.moises.movielist.domain.popular.repository.PopularMoviesRepository
import com.moises.movielist.domain.popular.usecase.GetAllPopularMoviesCoroutineUseCase
import com.moises.movielist.framework.datasource.popular.local.PopularMoviesLocalDataSourceImpl
import com.moises.movielist.framework.datasource.popular.mapper.FromMovieItemToMovie
import com.moises.movielist.framework.datasource.popular.mapper.FromMovieToPopularMoviesEntity
import com.moises.movielist.framework.datasource.popular.remote.PopularMoviesEndPoint
import com.moises.movielist.framework.datasource.popular.remote.PopularMoviesRemoteDataSourceImpl
import com.moises.movielist.framework.presentation.popular.PopularMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

fun providesPopularMoviesEndPoint(retrofit: Retrofit): PopularMoviesEndPoint =
    retrofit.create(PopularMoviesEndPoint::class.java)

fun providesPopularMoviesDao(moviesDatabase: MoviesDatabase): PopularMoviesDao =
    moviesDatabase.popularMoviesDao()

fun providesFromMovieItemToItem(): FromMovieItemToMovie = FromMovieItemToMovie()

fun providesPopularMoviesRemoteDataSource(
    popularMoviesEndPoint: PopularMoviesEndPoint,
    fromMovieItemToMovie: FromMovieItemToMovie
): PopularMoviesRemoteDataSource =
    PopularMoviesRemoteDataSourceImpl(popularMoviesEndPoint, fromMovieItemToMovie)

fun providesFromMovieToPopularMoviesEntity(): FromMovieToPopularMoviesEntity =
    FromMovieToPopularMoviesEntity()

fun providesPopularMoviesLocalDataSource(
    fromMovieToPopularMoviesEntity: FromMovieToPopularMoviesEntity,
    popularMoviesDao: PopularMoviesDao
): PopularMoviesLocalDataSource =
    PopularMoviesLocalDataSourceImpl(fromMovieToPopularMoviesEntity, popularMoviesDao)

fun providesPopularMoviesRepository(
    popularMoviesLocalDataSource: PopularMoviesLocalDataSource,
    popularMoviesRemoteDataSource: PopularMoviesRemoteDataSource
): PopularMoviesRepository =
    PopularMoviesRepositoryImpl(popularMoviesLocalDataSource, popularMoviesRemoteDataSource)

fun providesGetAllPopularMoviesUseCase(popularMoviesRepository: PopularMoviesRepository) :
        GetAllPopularMoviesCoroutineUseCase = GetAllPopularMoviesCoroutineUseCase(popularMoviesRepository)


val popularMoviesKoinModule = module {
    single { providesPopularMoviesEndPoint(get()) }
    single { providesPopularMoviesDao(get()) }
    single { providesFromMovieItemToItem() }
    single { providesPopularMoviesRemoteDataSource(get(), get()) }
    single { providesFromMovieToPopularMoviesEntity() }
    single { providesPopularMoviesLocalDataSource(get(), get())}
    single { providesPopularMoviesRepository(get(), get()) }
    single { providesGetAllPopularMoviesUseCase(get()) }
    viewModel { PopularMoviesViewModel(get()) }
}