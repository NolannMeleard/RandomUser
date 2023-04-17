package com.nmel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nmel.core_ui.ParentViewModel
import com.nmel.user.models.local.UserList
import com.nmel.user.network.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: UsersRepository) :
    ParentViewModel() {
    private val _usersData = MutableLiveData<UserList>()
    val usersData: LiveData<UserList> = _usersData

    fun getUsers() {
        compositeDisposable.add(
            repository.getUserByPage(1).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    Timber.d("$it")
                }, {
                    Timber.w(it)
                })
        )
    }
}