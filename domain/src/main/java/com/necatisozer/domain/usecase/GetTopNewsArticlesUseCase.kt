package com.necatisozer.domain.usecase

import com.necatisozer.common.helper.Result
import com.necatisozer.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTopNewsArticlesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend fun execute(params: Params = Params()) = withContext(Dispatchers.Default) {
        val topArticles = newsRepository.getTopArticles(params.sourceList, params.query)
        val readList = newsRepository.getReadList()

        // TODO: Write extension for this block
        if (topArticles.isSuccess && readList.isSuccess) {
            val readListUrls = readList.getOrNull()!!.map { it.url }
            Result.success(topArticles.getOrNull()!!.map {
                if (readListUrls.contains(it.url))
                    return@map it.copy(read = true)
                else
                    return@map it
            })
        } else if (topArticles.isFailure) {
            Result.failure(topArticles.exceptionOrNull()!!)
        } else {
            Result.failure(readList.exceptionOrNull()!!)
        }
    }

    data class Params(
        val sourceList: List<String>? = null,
        val query: String? = null
    )
}