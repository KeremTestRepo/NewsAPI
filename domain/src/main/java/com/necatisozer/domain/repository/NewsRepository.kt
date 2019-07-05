package com.necatisozer.domain.repository

import com.necatisozer.common.helper.Result
import com.necatisozer.domain.entity.Article
import com.necatisozer.domain.entity.Source

interface NewsRepository {
    suspend fun getSources(): Result<List<Source>>

    suspend fun getTopArticles(
        sourceList: List<String>? = null,
        query: String? = null
    ): Result<List<Article>>
}