package com.nmel.user.network.interfaces

import com.nmel.user.models.api.ApiRandomUserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
interface ApiUsersInterface {
    companion object {
        const val API = "api/"
    }

    @GET(API)
    fun getUsersByPage(@Query("page") page: Long, @Query("result") offset: Int = 20) : Single<ApiRandomUserResponse>
}