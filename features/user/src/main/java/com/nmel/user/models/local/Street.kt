package com.nmel.user.models.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Street(
    val number: Int,
    val name: String,
) : Parcelable {
    companion object {
        fun formatStreetToString(street: Street) = "${street.number} ${street.name}"
    }
}