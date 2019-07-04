package com.necatisozer.data.di

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.necatisozer.data.BuildConfig
import com.necatisozer.data.api.NewsApi
import com.necatisozer.data.repository.NewsDataRepository
import com.necatisozer.domain.repository.NewsRepository
import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.Moshi
import com.subgen.network.api.RequestInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
abstract class DataModule {
    @Binds
    abstract fun bindNewsDataRepository(newsDataRepository: NewsDataRepository): NewsRepository

    @Module
    companion object {
        private const val CACHE_SIZE_IN_BYTES = 10 * 1024 * 1024L
        private const val TIMEOUT_IN_SECONDS = 30L

        @JvmStatic
        @Provides
        @Singleton
        fun provideNewsApi(context: Context): NewsApi {
            val cache = Cache(File(context.cacheDir, "retrofit-cache"), CACHE_SIZE_IN_BYTES)

            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            }

            val requestInterceptor = RequestInterceptor()

            val okHttpClient = OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(requestInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(StethoInterceptor())
                .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .build()

            val moshi = Moshi.Builder()
                .add(Wrapped.ADAPTER_FACTORY)
                .build()

            val moshiConverterFactory = MoshiConverterFactory.create(moshi)

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.NEWS_API_BASE_URL)
                .addConverterFactory(moshiConverterFactory)
                .client(okHttpClient)
                .build()

            return retrofit.create()
        }
    }

}