package com.necatisozer.domain.usecase

import com.necatisozer.domain.entity.Article
import com.necatisozer.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddArticleToReadListUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend fun execute(params: Params) = withContext(Dispatchers.Default) {
        newsRepository.addToReadList(params.article)
    }

    data class Params(
        val article: Article
    )
}