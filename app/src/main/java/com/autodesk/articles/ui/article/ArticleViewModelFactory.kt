package com.autodesk.articles.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.autodesk.articles.repository.article.ArticlesRepository
import io.reactivex.disposables.CompositeDisposable

@Suppress("UNCHECKED_CAST")
class ArticleViewModelFactory(private val repository: ArticlesRepository,
                              private val disposables : CompositeDisposable) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArticlesViewModel(repository, disposables) as T
    }
}