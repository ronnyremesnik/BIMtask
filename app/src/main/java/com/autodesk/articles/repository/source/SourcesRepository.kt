package com.autodesk.articles.repository.source

import com.autodesk.articles.data.Source
import com.autodesk.articles.repository.BaseRepository
import com.autodesk.articles.util.doWorkOnBackgroundResultsOnMain

class SourcesRepository(
    private val localDataSource: SourceLocalDataSourceImpl,
    private val remoteDataSource: SourceRemoteDataSource
) : BaseRepository<Source>() {

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


}