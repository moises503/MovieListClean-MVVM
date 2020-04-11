package com.moises.movielist.framework.datasource.popular.remote

import com.moises.movielist.framework.datasource.popular.remote.model.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMoviesEndPoint {

    @GET("movie/popular")
    suspend fun retrievePopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int) : PopularMoviesResponse
}