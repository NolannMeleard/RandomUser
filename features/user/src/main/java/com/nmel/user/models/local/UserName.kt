package com.nmel.user.models.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserName(
    val title: String,
    val first: String,
    val last: String,
): Parcelable {
    companion object
}