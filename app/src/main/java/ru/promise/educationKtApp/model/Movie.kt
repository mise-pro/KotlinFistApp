package ru.promise.educationKtApp.model

import android.os.Parcelable
import java.io.Serializable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
        val id: Int,
        val pgAge: Int,
        val title: String,
        val genres: List<Genre>,
        val imageUrl: String,
        val detailImageUrl: String,
        val runningTime: Int,
        val isLiked: Boolean,
        val rating: Int,
        val storyLine: String,
        val reviewCount: Int,
        val actors: List<Actor>
) : Serializable, Parcelable