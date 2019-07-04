package com.necatisozer.newsapi

import android.app.Application
import com.necatisozer.common.extension.unsyncLazy
import com.necatisozer.newsapi.di.ApplicationComponent
import com.necatisozer.newsapi.di.DaggerApplicationComponent
import com.necatisozer.newsapi.di.DaggerComponentProvider

class BaseApplication : Application(), DaggerComponentProvider {
    override val component: ApplicationComponent by unsyncLazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }
}