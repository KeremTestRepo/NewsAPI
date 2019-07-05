package com.necatisozer.data.repository

import com.necatisozer.common.helper.Result
import com.necatisozer.common.helper.map
import com.necatisozer.data.api.NewsApi
import com.necatisozer.data.extension.networkRequest
import com.necatisozer.data.mapper.toArticleEntity
import com.necatisozer.data.mapper.toSourceEntity
import com.necatisozer.domain.entity.Article
import com.necatisozer.domain.repository.NewsRepository
import javax.inject.Inject

class NewsDataRepository @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    override suspend fun getSources() = networkRequest { newsApi.getSources() }.map { it.toSourceEntity() }

    override suspend fun getTopArticles(sourceList: List<String>?, query: String?): Result<List<Article>> {
        val sources = sourceList?.joinToString()
        return networkRequest { newsApi.getTopHeadlines(sources, query) }.map { it.toArticleEntity() }
    }

}