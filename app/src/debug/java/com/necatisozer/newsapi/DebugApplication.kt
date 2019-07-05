package com.necatisozer.newsapi

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import com.necatisozer.common.extension.unsyncLazy
import com.necatisozer.newsapi.di.ApplicationComponent
import com.necatisozer.newsapi.di.DaggerApplicationComponent
import com.necatisozer.newsapi.di.DaggerComponentProvider
import io.paperdb.Paper
import timber.log.Timber

class DebugApplication : Application(), DaggerComponentProvider {
    override val component: ApplicationComponent by unsyncLazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        Stetho.initializeWithDefaults(this)
        Timber.plant(Timber.DebugTree())
        Paper.init(this)
    }
}