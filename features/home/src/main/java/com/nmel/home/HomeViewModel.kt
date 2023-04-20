package com.nmel.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nmel.core_common.NetworkManager
import com.nmel.core_ui.ParentViewModel
import com.nmel.user.models.local.User
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
class HomeViewModel @Inject constructor(
    private val repository: UsersRepository,
    private val networkManager: NetworkManager
) : ParentViewModel() {
    private val _usersData = MutableLiveData<List<User>>()
    val usersData: LiveData<List<User>> = _usersData

    private val usersList = mutableStateListOf<User>()

    private var page by mutableStateOf(1)
    var canPaginate by mutableStateOf(false)
    var listState by mutableStateOf(ListState.IDLE)

    init {
        getUsers()
    }

    fun getUsers(shouldRefresh: Boolean = false) {
        if (isNetworkAvailable()) {
            clearData(shouldRefresh)
            listState = ListState.LOADING
            compositeDisposable.add(
                repository.getUserByPage(page = page.toLong())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe({
                        canPaginate = it.users.size == 20
                        usersList.addAll(it.users)
                        _usersData.postValue(usersList)
                        listState = ListState.IDLE
                        page++
                    }, {
                        listState = if (page == 1) {
                            getLocalData()
                            ListState.NOT_CONNECTED
                        } else ListState.ERROR
                    })
            )
        } else {
            getLocalData()
        }
    }

    private fun clearData(shouldRefresh: Boolean) {
        if (page == 1 || shouldRefresh) {
            compositeDisposable.add(
                repository.clearLocalUsers().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        usersList.clear()
                        _usersData.postValue(usersList)
                        page = 1
                        listState = ListState.LOADING
                    }, {})
            )
        }
    }

    private fun getLocalData() {
        if (usersList.size == 0) {
            compositeDisposable.add(
                repository.getLocalUsers().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        page = 2
                        listState = if (it.isNotEmpty()) {
                            _usersData.postValue(it)
                            ListState.NOT_CONNECTED
                        } else {
                            page = 1
                            ListState.ERROR
                        }
                    }, {
                        page = 1
                        listState = ListState.ERROR
                        Timber.w(it, "An error Occurred")
                    })
            )
        } else {
            listState = ListState.NOT_CONNECTED
        }
    }

    private fun isNetworkAvailable() = networkManager.isNetworkAvailable()
}

enum class ListState {
    IDLE,
    LOADING,
    ERROR,
    PAGINATION_EXHAUST,
    NOT_CONNECTED
}