package com.necatisozer.domain.usecase

import com.necatisozer.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTopNewsArticlesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend fun execute(params: Params = Params()) = withContext(Dispatchers.Default) {
        newsRepository.getTopArticles(params.sourceList, params.query)
    }

    data class Params(
        val sourceList: List<String>? = null,
        val query: String? = null
    )
}