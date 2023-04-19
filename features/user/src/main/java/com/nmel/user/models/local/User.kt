package com.nmel.user.models.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
@Entity(tableName = "users")
@Parcelize
data class User(
    @ColumnInfo(name = "name")
    val name: UserName,
    @ColumnInfo(name = "location")
    val location: Location,
    @ColumnInfo(name = "gender")
    val gender: String,
    @ColumnInfo(name = "picture")
    val picture: Picture,
    @ColumnInfo(name = "login")
    val login: Login,
    @ColumnInfo(name = "dob")
    val dob: Dob,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "cell")
    val cell: String,
    @ColumnInfo(name = "nat")
    val nat: String,
): Parcelable {
    companion object
}

data class Id(
    val name: String,
    val value: String,
)



