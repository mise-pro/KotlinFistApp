package ru.promise.educationKtApp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Actor(
        val name: String,
        val pic: String
) : Parcelable