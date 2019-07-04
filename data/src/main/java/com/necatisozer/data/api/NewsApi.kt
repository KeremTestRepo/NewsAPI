package com.necatisozer.data.api

import com.necatisozer.data.api.entity.Article
import com.necatisozer.data.api.entity.Source
import com.serjltt.moshi.adapters.Wrapped
import retrofit2.http.GET

interface NewsApi {
    @GET("sources")
    @Wrapped(path = ["sources"])
    suspend fun getSources(): List<Source>

    @GET("top-headlines")
    @Wrapped(path = ["articles"])
    suspend fun getTopHeadlines(): List<Article>

}