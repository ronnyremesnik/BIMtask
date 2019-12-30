package com.autodesk.articles.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.autodesk.articles.data.Article
import com.autodesk.articles.repository.ArticlesRepository
import com.autodesk.articles.ui.BaseDataContract
import com.autodesk.articles.util.publishSubjectToLiveData
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.w3c.dom.Entity

abstract class BaseViewModel<Entity, EntityResponse, Repository : BaseRepository<Entity, EntityResponse>>(
    protected val repository: Repository,
    private val disposable: CompositeDisposable
) : ViewModel(), BaseDataContract.ViewModel {

    private val entitiesList: LiveData<List<Entity>> by lazy {
        repository.incomingData.publishSubjectToLiveData(disposable)
    }

    fun getEntitiesObserver() = entitiesList

    private fun refreshData() {
        repository.refreshData()
    }

    override fun getEntitiesList() {
        if (entitiesList.value == null) {
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