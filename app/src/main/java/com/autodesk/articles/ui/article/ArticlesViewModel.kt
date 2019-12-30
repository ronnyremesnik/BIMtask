package com.autodesk.articles.ui.article

import com.autodesk.articles.data.Article
import com.autodesk.articles.repository.article.ArticlesRepository
import com.autodesk.articles.ui.BaseViewModel
import io.reactivex.disposables.CompositeDisposable

class ArticlesViewModel(
    private val repository: ArticlesRepository,
    disposable: CompositeDisposable
) : BaseViewModel<Article, ArticlesRepository>(repository, disposable) {

    fun getArticlesList(source: String) {
        if (entitiesList.value == null) {
            repository.getData(source)
        } else {
            repository.refreshData(source)
        }
    }

}