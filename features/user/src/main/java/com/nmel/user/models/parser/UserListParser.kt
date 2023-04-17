package com.nmel.user.models.parser

import com.nmel.user.models.api.ApiRandomUserResponse
import com.nmel.user.models.api.ApiUser
import com.nmel.user.models.local.User
import com.nmel.user.models.local.UserList

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
fun UserList.Companion.fromApiRandomUserResponse(apiRandomUserResponse: ApiRandomUserResponse): Result<UserList> {
    val userListParsed: List<User> =
        parseAssessmentList(apiRandomUserResponse.usersList)
    val userList = UserList(users = userListParsed)
    return Result.success(userList)
}

private fun parseAssessmentList(listApiUser: List<ApiUser>?): List<User> {
    return listApiUser?.mapNotNull { apiModelUser ->
        User.fromApiUser(
            apiModelUser
        ).getOrNull()
    }?.toList() ?: listOf()
}
