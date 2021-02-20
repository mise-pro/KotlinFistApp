package ru.promise.networktest.netModule.dataClass

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Config(
	//@SerialName("poster_sizes")
	//val posterSizes: List<String?>? = null,
	//val secureBaseUrl: String? = null,
	//val backdropSizes: List<String?>? = null,
	@SerialName("secure_base_url")
	val baseUrl: String,

	//val logoSizes: List<String>,
	//val stillSizes: List<String?>? = null,
	//val profileSizes: List<String?>? = null
)
@Serializable
data class ConfigResponse(
	@SerialName("images")
	val config: Config,
	//val changeKeys: List<String?>? = null
)

