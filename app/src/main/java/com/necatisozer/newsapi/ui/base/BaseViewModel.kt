package com.necatisozer.newsapi.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.necatisozer.common.exception.NetworkError
import com.necatisozer.common.exception.ServerError
import com.necatisozer.newsapi.R
import com.necatisozer.newsapi.helper.SingleLiveEvent
import kotlinx.coroutines.CancellationException

abstract class BaseViewModel : ViewModel() {
    protected val errorEvent = SingleLiveEvent<Int>()
    fun errorEvent(): LiveData<Int> = errorEvent

    protected fun handleFailure(throwable: Throwable) {
        if (throwable is CancellationException) return

        val errorMessage = when (throwable) {
            is NetworkError -> R.string.error_no_internet_connection
            is ServerError -> R.string.error_server
            else -> R.string.error_unknown
        }

        errorEvent.postValue(errorMessage)
    }
}