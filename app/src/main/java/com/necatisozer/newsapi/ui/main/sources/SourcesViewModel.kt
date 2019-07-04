package com.necatisozer.newsapi.ui.main.sources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.necatisozer.common.helper.onFailure
import com.necatisozer.common.helper.onSuccess
import com.necatisozer.domain.entity.Source
import com.necatisozer.domain.usecase.GetNewsSourcesUseCase
import com.necatisozer.newsapi.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SourcesViewModel @Inject constructor(
    private val getNewsSourcesUseCase: GetNewsSourcesUseCase
) : BaseViewModel() {
    private val sourcesLiveData = MutableLiveData<List<Source>>()
    fun sourcesLiveData(): LiveData<List<Source>> = sourcesLiveData

    init {
        viewModelScope.launch {
            getNewsSourcesUseCase.execute().onSuccess {
                sourcesLiveData.postValue(it)
            }.onFailure(::handleFailure)
        }
    }
}