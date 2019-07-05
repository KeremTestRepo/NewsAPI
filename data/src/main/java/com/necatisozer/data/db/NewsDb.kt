package com.necatisozer.data.db

import com.necatisozer.domain.entity.Article

interface NewsDb {
    suspend fun getReadList(): Set<Article>
    suspend fun addToReadList(article: Article)
    suspend fun removeFromReadList(article: Article)
}