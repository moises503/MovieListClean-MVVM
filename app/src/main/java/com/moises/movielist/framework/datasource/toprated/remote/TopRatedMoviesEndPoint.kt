package com.moises.movielist.framework.datasource.toprated.remote

import com.moises.movielist.framework.datasource.toprated.remote.model.TopRatedMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TopRatedMoviesEndPoint {

    @GET("movie/top_rated")
    suspend fun retrievePopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int) : TopRatedMoviesResponse
}