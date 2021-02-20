package ru.promise.educationKtApp.model

import java.io.Serializable

@kotlinx.serialization.Serializable
data class Actor(
    val id: Int,
    var name: String,
    var imageUrl: String
) : Serializable {
    override fun toString(): String {
        return name
    }
}