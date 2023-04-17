package com.nmel.user.models.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Login(
    val username: String,
): Parcelable {
    companion object
}