package ru.promise.educationKtApp.model

import kotlinx.serialization.Serializable

@Serializable
data class Genre(val id: Int, var name: String) {
    override fun toString(): String {
        return name
    }
}
