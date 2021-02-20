package ru.promise.networktest.netModule.dataClass

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviePopularResponse(
	//val page: Int,
	//val totalPages: Int,
	@SerialName("results")
	val results: List<MovieItem>,
	//val totalResults: Int
)
@Serializable
data class MovieItem(
	@SerialName("overview")
	val overview: String,
	//val originalLanguage: String,

	//val originalTitle: String,
	val video: Boolean,
	@SerialName("title")
	val title: String,
	@SerialName("genre_ids")
	val genreIds: List<Int>,
	@SerialName("poster_path")
	val posterPath: String,
	//val backdropPath: String,
	//val releaseDate: String,
	//val popularity: Double,
	@SerialName("vote_average")
	val voteAverage: Double,
	@SerialName("id")
	val id: Int,
	//val adult: Boolean,
	@SerialName("vote_count")
	val voteCount: Int
)

