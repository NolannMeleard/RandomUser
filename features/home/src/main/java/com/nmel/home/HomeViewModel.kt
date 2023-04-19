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
import com.nmel.user.storage.UsersDao
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
    private val usersDao: UsersDao,
    private val networkManager: NetworkManager
) : ParentViewModel() {

    private val _usersData = MutableLiveData<List<User>>()
    val usersData: LiveData<List<User>> = _usersData

    val usersList = mutableStateListOf<User>()

    private var page by mutableStateOf(1)
    var canPaginate by mutableStateOf(false)
    var listState by mutableStateOf(ListState.IDLE)

    init {
        if (isNetworkAvailable())
            getUsers()
    }

    fun getUsers() {
        listState = if (page == 1) ListState.LOADING else ListState.PAGINATING
        compositeDisposable.add(
            repository.getUserByPage(page = page.toLong())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    canPaginate = it.users.size == 20
                    Timber.d("value $it")
                    if (page == 1)
                        usersDao.clearAllUsers()
                    usersDao.insertAll(it.users)
                    usersList.addAll(it.users)
                    _usersData.postValue(usersList)
                    listState = ListState.IDLE
                    page++
                }, {
                    listState = if (page == 1) ListState.ERROR else ListState.PAGINATION_EXHAUST
                    Timber.w(it)
                })
        )
    }

    fun isNetworkAvailable() = networkManager.isNetworkAvailable()
}

enum class ListState {
    IDLE,
    LOADING,
    PAGINATING,
    ERROR,
    PAGINATION_EXHAUST,
    NOT_CONNECTED
}