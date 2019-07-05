package com.necatisozer.newsapi

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.necatisozer.common.extension.unsyncLazy
import com.necatisozer.newsapi.di.ApplicationComponent
import com.necatisozer.newsapi.di.DaggerApplicationComponent
import com.necatisozer.newsapi.di.DaggerComponentProvider
import io.paperdb.Paper

class BaseApplication : Application(), DaggerComponentProvider {
    override val component: ApplicationComponent by unsyncLazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        Paper.init(this)
    }
}