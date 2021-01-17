package ru.promise.educationKtApp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
        val name: String,
        val poster: String,
        val pg: Int,
        val tagline: String,
        val rating: Int,
        val reviews: Int,
        val storyLine: String,
        val duration: Int,
        val actorList: List<Actor>
)  : Parcelable

