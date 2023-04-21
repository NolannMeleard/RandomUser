package com.nmel.user.models.parser

import com.nmel.user.models.api.ApiRandomUserResponse
import com.nmel.user.models.api.ApiUser
import com.nmel.user.models.local.RandomUserResponse
import com.nmel.user.models.local.User

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
fun RandomUserResponse.Companion.fromApiRandomUserResponse(apiRandomUserResponse: ApiRandomUserResponse): Result<RandomUserResponse> {
    val userListParsed: List<User> =
        parseAssessmentList(apiRandomUserResponse.usersList)

    val randomUserResponse = RandomUserResponse(users = userListParsed)

    return Result.success(randomUserResponse)
}

private fun parseAssessmentList(listApiUser: List<ApiUser>?): List<User> {
    return listApiUser?.mapNotNull { apiModelUser ->
        User.fromApiUser(
            apiModelUser
        ).getOrNull()
    }?.toList() ?: listOf()
}
