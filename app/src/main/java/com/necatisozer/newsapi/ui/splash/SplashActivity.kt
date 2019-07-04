package com.necatisozer.newsapi.ui.splash

import com.necatisozer.newsapi.R
import com.necatisozer.newsapi.di.injector
import com.necatisozer.newsapi.extension.observe
import com.necatisozer.newsapi.extension.viewModels
import com.necatisozer.newsapi.ui.base.BaseActivity
import com.necatisozer.newsapi.ui.main.MainActivity
import splitties.activities.start
import splitties.toast.toast

class SplashActivity : BaseActivity() {
    private val viewModel by viewModels { injector.splashViewModel }

    override fun initView() {}

    override fun observeViewModels() {
        viewModel.mainEvent().observe(this) { openMain() }
        viewModel.errorEvent().observe(this) { toast(R.string.no_internet_connection) }
    }

    private fun openMain() {
        start<MainActivity>()
        finish()
    }
}