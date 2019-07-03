package com.necatisozer.domain.usecase

import com.necatisozer.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetNewsSourcesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend fun execute() = withContext(Dispatchers.Default) {
        newsRepository.getSources()
    }
}