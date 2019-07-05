package com.necatisozer.newsapi.ui.main.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.necatisozer.common.helper.onFailure
import com.necatisozer.common.helper.onSuccess
import com.necatisozer.domain.entity.Article
import com.necatisozer.domain.usecase.AddArticleToReadListUseCase
import com.necatisozer.domain.usecase.GetTopNewsArticlesUseCase
import com.necatisozer.domain.usecase.RemoveArticleFromReadListUseCase
import com.necatisozer.newsapi.helper.SingleLiveEvent
import com.necatisozer.newsapi.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(
    private val getTopNewsArticlesUseCase: GetTopNewsArticlesUseCase,
    private val addArticleToReadListUseCase: AddArticleToReadListUseCase,
    private val removeArticleFromReadListUseCase: RemoveArticleFromReadListUseCase
) : BaseViewModel() {
    private val articlesLiveData = MutableLiveData<List<Article>>()
    fun articlesLiveData(): LiveData<List<Article>> = articlesLiveData

    private val newArticlesEvent = SingleLiveEvent<Void>()
    fun newArticlesEvent(): LiveData<Void> = newArticlesEvent

    private lateinit var job: Job
    private lateinit var sourceId: String
    private var latestArticle: Article? = null

    fun init(sourceId: String) {
        this.sourceId = sourceId
        fetchTopArticles(null)
    }

    fun fetchTopArticles(query: String?) {
        val params = GetTopNewsArticlesUseCase.Params(sourceList = listOf(sourceId), query = query)

        if (::job.isInitialized) job.cancel()

        job = viewModelScope.launch {
            while (true) {
                getTopNewsArticlesUseCase.execute(params).onSuccess {
                    articlesLiveData.postValue(it)

                    val newestArticle = it.getOrNull(0)

                    if (latestArticle == null) {
                        latestArticle = newestArticle
                    } else if (newestArticle.isNewerThan(latestArticle)) {
                        latestArticle = newestArticle
                        newArticlesEvent.call()
                    }
                }.onFailure(::handleFailure)

                delay(ARTICLES_REFRESH_TIME)
            }
        }
    }

    fun onReadListStatusChange(article: Article) {
        viewModelScope.launch {
            if (article.read) {
                addArticleToReadListUseCase.execute(AddArticleToReadListUseCase.Params(article))
            } else {
                removeArticleFromReadListUseCase.execute(RemoveArticleFromReadListUseCase.Params(article))
            }
        }
    }

    private fun Article?.isNewerThan(article: Article?): Boolean {
        val thisTime = this?.time
        val articleTime = article?.time

        return if (thisTime == null || articleTime == null) false
        else thisTime > articleTime
    }

    companion object {
        val ARTICLES_REFRESH_TIME = TimeUnit.MINUTES.toMillis(1)
    }
}