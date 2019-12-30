package com.autodesk.articles.repository.source

import com.autodesk.articles.data.Source
import com.autodesk.articles.util.doWorkOnBackgroundResultsOnMain
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber



class SourcesRepository(
    private val localDataSource: SourceLocalDataSourceImpl,
    private val remoteDataSource: SourceRemoteDataSource
) {

    val incomingData: PublishSubject<List<Source>> = PublishSubject.create<List<Source>>()

    val disposable: CompositeDisposable = CompositeDisposable()

    // get data from local db
    fun getData() {
        disposable.add(
            localDataSource.getSources()
                .doWorkOnBackgroundResultsOnMain()
                .doAfterNext {
                    refreshData()
                }
                .subscribe({ listArticles ->
                    incomingData.onNext(listArticles)
                }, { error ->
                    handleError(error)
                })
        )
    }

    // get data from remote - api
    fun refreshData() {
        disposable.add(
            remoteDataSource.getSources()
                .doWorkOnBackgroundResultsOnMain()
                .subscribe({ data -> saveData(data.sources) },
                    { error -> handleError(error) })
        )
    }

    // save data to db
    private fun saveData(data: List<Source>) {
        localDataSource.saveSources(data)
    }

    private fun handleError(error: Throwable) {
        Timber.e(error)
    }

    fun onClear() {
        disposable.clear()
    }


}