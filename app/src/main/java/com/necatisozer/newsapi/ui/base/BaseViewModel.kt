package com.necatisozer.newsapi.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.necatisozer.newsapi.helper.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {
    protected val errorEvent = SingleLiveEvent<Void>()
    fun errorEvent(): LiveData<Void> = errorEvent
}