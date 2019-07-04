package com.necatisozer.newsapi.di

import android.content.Context
import com.necatisozer.data.di.DataModule
import com.necatisozer.newsapi.ui.splash.SplashViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    val splashViewModel: SplashViewModel
}