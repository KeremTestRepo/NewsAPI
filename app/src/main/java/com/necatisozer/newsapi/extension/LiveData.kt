package com.necatisozer.newsapi.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, body: (T?) -> Unit) {
    observe(owner, Observer { body(it) })
}