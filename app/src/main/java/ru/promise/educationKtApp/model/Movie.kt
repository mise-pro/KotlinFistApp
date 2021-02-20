package ru.promise.educationKtApp.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val pgAge: Int,
    val title: String,
    var genres: List<Genre>,
    var imageUrl: String,
    var detailImageUrl: String,
    val runningTime: Int,
    var isLiked: Boolean,
    val rating: Int,
    val storyLine: String,
    var reviewCount: Int,
    var actors: List<Actor>
) {
    override fun toString(): String {
        return this.title
    }
}