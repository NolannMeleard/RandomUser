package com.nmel.core_common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import timber.log.Timber

/**
 * Created by Nolann Méléard on 17 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
class NetworkManager(context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    /**
     * Check the connectivity status
     *
     * @return True if internet available
     */
    private fun checkNetworkAvailable(): Boolean {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
            } else {
                val networks = connectivityManager.allNetworks
                for (network in networks) {
                    val nInfo = connectivityManager.getNetworkInfo(network)
                    if (nInfo != null && nInfo.isConnected) {
                        return true
                    }
                }
            }

            return false
        } catch (ex: Exception) {
            Timber.w(ex)
            return true
        }
    }

    /**
     * Check the connectivity status
     *
     * @return True if internet available
     */
    fun isNetworkAvailable(): Boolean = checkNetworkAvailable()
}