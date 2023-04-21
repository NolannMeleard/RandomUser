package com.nmel.user.network.repository

import com.nmel.user.models.local.RandomUserResponse
import com.nmel.user.models.local.User
import com.nmel.user.models.parser.fromApiRandomUserResponse
import com.nmel.user.network.interfaces.ApiUsersInterface
import com.nmel.user.storage.UsersDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
class UsersRepository @Inject constructor(
    private val apiUsersInterface: ApiUsersInterface,
    private val usersDao: UsersDao
) {
    fun getUserByPage(page: Long): Single<RandomUserResponse> {
        return apiUsersInterface.getUsersByPage(page = page).subscribeOn(Schedulers.io())
            .doOnSuccess {
                RandomUserResponse.fromApiRandomUserResponse(it).getOrNull()?.let { response ->
                    usersDao.insertAll(response.users)
                }
            }.flatMap {
            val resultUsers = RandomUserResponse.fromApiRandomUserResponse(it)

            resultUsers.fold(
                onSuccess = { users -> return@fold Single.just(users) },
                onFailure = { exception -> return@fold Single.error(exception) }
            )
        }
    }

    fun getLocalUsers(): Single<List<User>> {
        return usersDao.getUsers()
    }
    fun clearLocalUsers(): Completable = usersDao.clearAllUsers()
}