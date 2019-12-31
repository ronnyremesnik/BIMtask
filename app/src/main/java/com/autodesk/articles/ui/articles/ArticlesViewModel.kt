package com.autodesk.articles.ui.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.autodesk.articles.data.Article
import com.autodesk.articles.repository.article.ArticlesRepository
import com.autodesk.articles.util.publishSubjectToLiveData
import io.reactivex.disposables.CompositeDisposable

class ArticlesViewModel(
    private val repository: ArticlesRepository,
    private val disposable: CompositeDisposable
) : ViewModel() {

    val articlesList: LiveData<List<Article>> by lazy {
        repository.incomingData.publishSubjectToLiveData(disposable)
    }

    private fun refreshData() {
        repository.refreshData()
    }

    fun getArticlesList() {
        if (articlesList.value == null) {
            repository.getData()
        } else {
            refreshData()
        }
    }

    override fun onCleared() {
        super.onCleared()

        //clear the disposable when the viewmodel is cleared
        disposable.clear()
        repository.disposable.clear()
    }
}