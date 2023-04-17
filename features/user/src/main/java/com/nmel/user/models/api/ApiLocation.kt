package com.nmel.user.models.api

import com.google.gson.annotations.SerializedName

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
data class ApiLocation(
    @SerializedName("street")
    val street: ApiStreet?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("state")
    val state: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("postcode")
    val postcode: String?,
    @SerializedName("coordinates")
    val coordinates: ApiCoordinates?
)

data class ApiStreet(
    @SerializedName("number")
    val number: Int?,
    @SerializedName("name")
    val name: String?
)

data class ApiCoordinates(
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("longitude")
    val longitude: String?
)

