package com.necatisozer.common.extension

fun <T> unsyncLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)