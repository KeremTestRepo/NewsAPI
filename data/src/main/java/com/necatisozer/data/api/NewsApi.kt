package com.necatisozer.data.api

import com.necatisozer.data.api.entity.Article
import com.necatisozer.data.api.entity.Source
import com.serjltt.moshi.adapters.Wrapped
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("sources")
    @Wrapped(path = ["sources"])
    suspend fun getSources(): List<Source>

    @GET("top-headlines")
    @Wrapped(path = ["articles"])
    suspend fun getTopHeadlines(
        @Query("sources") sources: String? = null,
        @Query("q") query: String? = null
    ): List<Article>

}