package ru.promise.networktest.netModule.dataClass

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
	val cast: List<CastItem>,
	//val id: Int,
	//val crew: List<CrewItem>
)

data class CrewItem(
	val gender: Int,
	val creditId: String,
	val knownForDepartment: String,
	val originalName: String,
	val popularity: Double,
	val name: String,
	val profilePath: String,
	val id: Int,
	val adult: Boolean,
	val department: String,
	val job: String
)
@Serializable
data class CastItem(
//	val castId: Int,
//	val character: String,
//	val gender: Int,
//	val creditId: String,
//	val knownForDepartment: String,
//	val originalName: String,
//	val popularity: Double,
	@SerialName("name")
	val name: String?,
	@SerialName("profile_path")
	val profilePath: String?,
	@SerialName("id")
	val id: Int,
	//val adult: Boolean,
	//val order: Int
)

