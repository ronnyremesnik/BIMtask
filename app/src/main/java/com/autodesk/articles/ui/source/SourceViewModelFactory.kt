package com.autodesk.articles.ui.source

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.autodesk.articles.repository.ArticlesRepository
import com.autodesk.articles.repository.SourcesRepository
import io.reactivex.disposables.CompositeDisposable

@Suppress("UNCHECKED_CAST")
class SourceViewModelFactory(private val repository: SourcesRepository,
                             private val disposables : CompositeDisposable) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SourceViewModel(repository, disposables) as T
    }
}