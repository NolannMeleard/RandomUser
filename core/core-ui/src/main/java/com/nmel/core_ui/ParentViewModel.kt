package com.nmel.core_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nmel.core_common.NetworkManager
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
abstract class ParentViewModel : ViewModel() {
    @Inject
    lateinit var networkManager: NetworkManager
    protected val compositeDisposable = CompositeDisposable()

    /**
     * Call when the viewModel is finished
     */
    override fun onCleared() {
        super.onCleared()
        // Clear all disposables ref saved to avoid memory leaks
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }

    fun isNetworkAvailable() = networkManager.isNetworkAvailable()
}
