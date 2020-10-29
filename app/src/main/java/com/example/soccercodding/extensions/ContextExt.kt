package com.example.soccercodding.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * Returns true if the device has internet connection
 */

// extension function for Context --> which is an interface for the information about an application environment
val Context.isConnected: Boolean
    get() {
        // initializing the connectivityManager
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return when {
            // comparing the sdk code of the current device against the known
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                // checking if there's connection to the network
                val nw = connectivityManager.activeNetwork ?: return false
                // checking the properties of the connection to the network
                val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
                when {
                    // when statement for
                    // setting the active network capabilities to wifi
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    // setting the active network capabilities to cellular data
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    // if no active network then return false
                    else -> false
                }
            }
            else -> {
                // Use depreciated methods only on older devices
                val nwInfo = connectivityManager.activeNetworkInfo ?: return false
                nwInfo.isConnected
            }
        }
    }