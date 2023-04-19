package com.nmel.user.models.api

import com.google.gson.annotations.SerializedName

/**
 * Created by Nolann Méléard on 18 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
data class ApiInfo(
    @SerializedName("seed")
    val seed: String?,
    @SerializedName("page")
    val page: Int?
)