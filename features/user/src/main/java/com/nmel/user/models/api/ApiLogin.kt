package com.nmel.user.models.api

import com.google.gson.annotations.SerializedName

class ApiLogin(
    @SerializedName("username")
    val username: String?
)
