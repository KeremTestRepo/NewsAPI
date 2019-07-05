package com.necatisozer.data.db.paper

import com.necatisozer.data.db.NewsDb
import com.necatisozer.domain.entity.Article
import io.paperdb.Paper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PaperDb @Inject constructor() : NewsDb {
    override suspend fun getReadList() = withContext(Dispatchers.IO) {
        Paper.book().read<Set<Article>>(READ_LIST, setOf())
    }

    override suspend fun addToReadList(article: Article) = withContext(Dispatchers.IO) {
        val readList = getReadList().toMutableSet()
        readList.add(article)
        Paper.book().write(READ_LIST, readList.toSet())
        return@withContext
    }

    override suspend fun removeFromReadList(article: Article) = withContext(Dispatchers.IO) {
        val readList = getReadList().toMutableSet()
        readList.remove(article)
        Paper.book().write(READ_LIST, readList.toSet())
        return@withContext
    }

    companion object {
        private const val READ_LIST = "read-list"
    }

}