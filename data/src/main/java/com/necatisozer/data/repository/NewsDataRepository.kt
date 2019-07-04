package com.necatisozer.data.repository

import com.necatisozer.common.helper.map
import com.necatisozer.data.api.NewsApi
import com.necatisozer.data.extension.networkRequest
import com.necatisozer.data.mapper.toArticleEntity
import com.necatisozer.data.mapper.toSourceEntity
import com.necatisozer.domain.repository.NewsRepository
import javax.inject.Inject

class NewsDataRepository @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    override suspend fun getSources() = networkRequest { newsApi.getSources() }.map { it.toSourceEntity() }
    override suspend fun getTopArticles() = networkRequest { newsApi.getTopHeadlines() }.map { it.toArticleEntity() }
}