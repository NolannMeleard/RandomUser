package com.nmel.core_ui

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import timber.log.Timber

/**
 * Created by Nolann Méléard on 16 April 2023.
 * Kiplin
 * nolann.meleard@kiplin.com
 */
open class ParentFragment: Fragment() {
    /**
     * Perform a navigate action securely
     * Avoid Double call (on same action or different action)
     * that guide to an exception : cause the second action is called on the next view of the first
     * action (where this action do not exist)
     */
    fun navigate(destination: NavDirections) = with(findNavController()) {
        currentDestination?.getAction(destination.actionId)
            ?.let { navigate(destination) }
    }

    /**
     * Perform a popback from the navController
     * Avoid double call that can guide to bug
     */
    fun popBackStack() {
        val navController = findNavController()
        try {
            val destination = navController.currentDestination as FragmentNavigator.Destination
            if (javaClass.name == destination.className) {
                navController.popBackStack()
            }
        } catch (e: Exception) {
            Timber.w(e)
        }
    }

    /**
     * Perform a navigateUp from the navController
     * Avoid double call that can guide to bug
     */
    fun navigateUp() {
        val navController = findNavController()
        try {
            val destination =
                navController.currentDestination as FragmentNavigator.Destination
            if (javaClass.name == destination.className) {
                navController.navigateUp()
            }
        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}