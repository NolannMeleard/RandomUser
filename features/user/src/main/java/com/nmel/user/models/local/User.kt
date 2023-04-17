package com.nmel.user.models.local

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
@Entity
@Parcelize
data class User(
    val name: UserName,
    val location: Location,
    val gender: String,
    val picture: Picture,
    val login: Login,
    val dob: Dob,
    val email: String,
    val phone: String,
    val cell: String,
    val nat: String,
): Parcelable {
    companion object
}

data class Id(
    val name: String,
    val value: String,
)



