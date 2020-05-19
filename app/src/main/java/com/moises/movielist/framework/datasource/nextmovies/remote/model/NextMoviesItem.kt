package com.moises.movielist.framework.dasource.nextmovies.remote.model

import com.google.gson.annotations.SerializedName

data class NextMoviesItem(

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("original_language")
	val originalLanguage: String,

	@field:SerializedName("original_title")
	val originalTitle: String,

	@field:SerializedName("video")
	val video: Boolean,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("genre_ids")
	val genreIds: List<Int>,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("popularity")
	val popularity: Double? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("adult")
	val adult: Boolean,

	@field:SerializedName("vote_count")
	val voteCount: Int? = null
)