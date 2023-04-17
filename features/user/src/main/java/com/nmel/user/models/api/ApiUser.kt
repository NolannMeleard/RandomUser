package com.nmel.user.models.api

import com.google.gson.annotations.SerializedName

/**
 * Created by Nolann Méléard on 16 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

data class ApiUser(
    @SerializedName("name")
    val name: ApiUserName?,
    @SerializedName("location")
    val location: ApiLocation?,
    @SerializedName("gender")
    val gender:String?,
    @SerializedName("picture")
    val picture: ApiPicture?,
    @SerializedName("login")
    val login: ApiLogin?,
    @SerializedName("dob")
    val dob: ApiDob?,
    @SerializedName("email")
    val email:String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("cell")
    val cell:String?,
    @SerializedName("nat")
    val nat: String?,
)