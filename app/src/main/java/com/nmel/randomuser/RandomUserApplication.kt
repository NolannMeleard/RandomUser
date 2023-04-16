package com.nmel.randomuser

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Nolann Méléard on 14 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */

@HiltAndroidApp
class RandomUserApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}