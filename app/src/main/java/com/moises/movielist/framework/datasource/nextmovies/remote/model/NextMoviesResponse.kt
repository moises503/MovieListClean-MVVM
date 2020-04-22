package com.moises.movielist.framework.dasource.nextmovies.remote.model

import com.google.gson.annotations.SerializedName
import com.moises.movielist.framework.dasource.nextmovies.remote.model.NextMoviesItem

data class NextMoviesResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<NextMoviesItem>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)