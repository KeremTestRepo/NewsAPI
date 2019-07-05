package com.necatisozer.data.api

import com.necatisozer.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val requestBuilder = request.newBuilder()

        requestBuilder.addHeader("X-Api-Key", BuildConfig.NEWS_API_KEY)

        val defaultLocale = Locale.getDefault()

        val httpUrl = request.url.newBuilder()
            //.addQueryParameter("country", defaultLocale.country)
            //.addQueryParameter("language", defaultLocale.language)
            .build()

        request = requestBuilder.url(httpUrl).build()

        return chain.proceed(request)
    }

}