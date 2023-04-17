package com.nmel.user.models.api

import com.google.gson.annotations.SerializedName

data class ApiPicture(
    @SerializedName("large")
    val large: String?,
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?
)
