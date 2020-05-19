package com.moises.movielist.framework.dasource.nextmovies.remote

import com.moises.movielist.framework.dasource.nextmovies.remote.model.NextMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NextMoviesEndPoint {

    @GET("movie/upcoming")
    suspend fun retrieveNextMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int) : NextMoviesResponse
}