package com.necatisozer.newsapi.ui.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import com.necatisozer.newsapi.di.injector
import com.necatisozer.newsapi.extension.viewModels
import com.necatisozer.newsapi.ui.base.BaseActivity
import com.necatisozer.newsapi.ui.main.MainActivity
import splitties.activities.start
import splitties.toast.toast

class SplashActivity : BaseActivity() {
    private val viewModel by viewModels { injector.splashViewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModels()
    }

    private fun observeViewModels() {
        viewModel.mainEvent().observe(this, Observer { openMain() })
        viewModel.errorEvent().observe(this, Observer { toast(it) })
    }

    private fun openMain() {
        start<MainActivity>()
        finish()
    }
}