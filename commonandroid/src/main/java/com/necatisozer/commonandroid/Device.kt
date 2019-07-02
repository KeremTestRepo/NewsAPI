package com.necatisozer.commonandroid

import splitties.systemservices.connectivityManager

object Device {
    val hasInternetConnection get() = connectivityManager.activeNetworkInfo?.isConnectedOrConnecting == true
}