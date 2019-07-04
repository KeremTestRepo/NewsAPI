package com.necatisozer.newsapi.ui.splash

import android.os.Bundle
import com.necatisozer.newsapi.di.injector
import com.necatisozer.newsapi.extension.viewModels
import com.necatisozer.newsapi.ui.base.BaseActivity
import com.necatisozer.newsapi.ui.main.MainActivity
import splitties.activities.start
import splitties.arch.lifecycle.observeNotNull
import splitties.toast.toast

class SplashActivity : BaseActivity() {
    private val viewModel by viewModels { injector.splashViewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModels()
    }

    private fun observeViewModels() {
        observeNotNull(viewModel.mainEvent()) { openMain() }
        observeNotNull(viewModel.errorEvent()) { toast(it) }
    }

    private fun openMain() {
        start<MainActivity>()
        finish()
    }
}