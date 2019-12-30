package com.autodesk.articles.ui.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.autodesk.articles.data.Source
import com.autodesk.articles.data.SourceResponse
import com.autodesk.articles.repository.SourcesRepository
import com.autodesk.articles.ui.BaseViewModel
import com.autodesk.articles.util.publishSubjectToLiveData
import io.reactivex.disposables.CompositeDisposable

class SourceViewModel(
    private val repository: SourcesRepository,
    private val disposable: CompositeDisposable
) : ViewModel() {

    val sourceList: LiveData<List<Source>> by lazy {
        repository.incomingData.publishSubjectToLiveData(disposable)
    }

    fun getSourcesList() {
        if (sourceList.value == null) {
            repository.getData()
        } else {
            repository.refreshData()
        }
    }

    override fun onCleared() {
        //clear the disposable when the viewmodel is cleared
        disposable.clear()
        repository.disposable.clear()
    }
}