package com.necatisozer.newsapi.ui.splash

import androidx.lifecycle.LiveData
import com.necatisozer.commonandroid.Device
import com.necatisozer.newsapi.helper.SingleLiveEvent
import com.necatisozer.newsapi.ui.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {
    private val mainEvent = SingleLiveEvent<Void>()
    fun mainEvent(): LiveData<Void> = mainEvent

    init {
        when {
            Device.hasInternetConnection -> mainEvent.call()
            else -> errorEvent.call()
        }
    }
}