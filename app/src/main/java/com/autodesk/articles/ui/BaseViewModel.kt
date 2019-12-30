package com.autodesk.articles.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.autodesk.articles.data.Displayable
import com.autodesk.articles.repository.BaseRepository
import com.autodesk.articles.util.publishSubjectToLiveData
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<Entity : Displayable, Repository : BaseRepository<Entity>>
    (
    private val repository: Repository,
    private val disposable: CompositeDisposable
) : ViewModel() {

    val entitiesList: LiveData<List<Entity>> by lazy {
        repository.incomingData.publishSubjectToLiveData(disposable)
    }

    override fun onCleared() {
        disposable.clear()
        repository.disposable.clear()
    }
}