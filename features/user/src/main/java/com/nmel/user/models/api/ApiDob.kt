package com.nmel.user.models.api

import com.google.gson.annotations.SerializedName

/**
 * Created by Nolann Méléard on 16 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

data class ApiDob(
    @SerializedName("date")
    val date: String?,
    @SerializedName("age")
    val age: Int?
)