package com.necatisozer.newsapi.extension

import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.View.*

fun View.show() {
    visibility = VISIBLE
}

fun View.hide() {
    visibility = GONE
}

fun View.invisible() {
    visibility = INVISIBLE
}

val View.inflater: LayoutInflater get() = LayoutInflater.from(context)

fun View.onSingleClick(debounceTime: Long = 2000L, action: () -> Unit) {
    var lastClickTime: Long = 0
    setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime >= debounceTime) action()
        lastClickTime = SystemClock.elapsedRealtime()
    }
}
