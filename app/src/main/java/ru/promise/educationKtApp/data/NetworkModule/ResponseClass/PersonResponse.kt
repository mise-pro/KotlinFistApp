package ru.promise.networktest.netModule.dataClass

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonResponse(
	//val birthday: String,
	//val alsoKnownAs: List<String>,
	//val gender: Int,
	//val imdbId: String,
	//val knownForDepartment: String,
	@SerialName("profile_path")
	val profilePath: String?,
	//val biography: String,
	//val deathday: Any,
	//val placeOfBirth: String,
	//val popularity: Double,
	@SerialName("name")
	val name: String,
	@SerialName("id")
	val id: Int,
	//val adult: Boolean,
	//val homepage: Any
)

