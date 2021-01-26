package ru.promise.educationKtApp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Actor(
    val id: Int,
    val name: String,
    val imageUrl: String
) : Serializable, Parcelable {
    override fun toString(): String {
        return name
    }
}