package com.autodesk.articles.ui.article

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

    fun getArticlesList(source: String) {
        if (articlesList.value == null) {
            repository.getData(source)
        } else {
            repository.refreshData(source)
        }
    }

    override fun onCleared() {
        //clear the disposable when the viewmodel is cleared
        disposable.clear()
        repository.disposable.clear()
    }
}