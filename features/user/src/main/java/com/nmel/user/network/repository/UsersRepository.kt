package com.nmel.user.network.repository

import com.nmel.user.models.local.UserList
import com.nmel.user.models.parser.fromApiRandomUserResponse
import com.nmel.user.network.interfaces.ApiUsersInterface
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
class UsersRepository(private val apiUsersInterface: ApiUsersInterface) {
    fun getUserByPage(page: Long): Single<UserList> {
        return apiUsersInterface.getUsersByPage(page = page).subscribeOn(Schedulers.io()).flatMap {
            val resultUsers = UserList.fromApiRandomUserResponse(it)

            resultUsers.fold(
                onSuccess = { users -> return@fold Single.just(users) },
                onFailure = { exception -> return@fold Single.error(exception) }
            )
        }
    }


}