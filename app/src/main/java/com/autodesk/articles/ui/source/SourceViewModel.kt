package com.autodesk.articles.ui.source

import com.autodesk.articles.data.Source
import com.autodesk.articles.repository.source.SourcesRepository
import com.autodesk.articles.ui.BaseViewModel
import io.reactivex.disposables.CompositeDisposable

class SourceViewModel(
    private val repository: SourcesRepository,
    disposable: CompositeDisposable
) : BaseViewModel<Source, SourcesRepository>(repository, disposable) {

    fun getSourcesList() {
        if (entitiesList.value == null) {
            repository.getData()
        } else {
            repository.refreshData()
        }
    }

}