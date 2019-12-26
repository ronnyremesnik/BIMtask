package com.autodesk.articles.repository

import com.autodesk.articles.data.Article
import com.autodesk.articles.main_screen.MainScreenDataContract
import com.autodesk.articles.util.doWorkOnBackgroundResultsOnMain
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

class ArticlesRepository(
    private val localDataSource: MainScreenDataContract.LocalDataSource,
    private val remoteDataSource: MainScreenDataContract.RemoteDataSource
) : MainScreenDataContract.Repository {

    override val incomingData: PublishSubject<List<Article>> = PublishSubject.create<List<Article>>()

    override val disposable: CompositeDisposable = CompositeDisposable()

    // get data from local db
    override fun getData() {
        disposable.add(
            localDataSource.getArticles()
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
    override fun refreshData() {
        disposable.add(
            remoteDataSource.getArticles()
                .doWorkOnBackgroundResultsOnMain()
                .subscribe({ data -> saveData(data.articles) },
                    { error -> handleError(error) })
        )
    }

    // save data to db
    override fun saveData(data: List<Article>) {
        localDataSource.saveArticles(data)
    }

    override fun handleError(error: Throwable) {
        Timber.e(error)
    }

    override fun onClear() {
        disposable.clear()
    }


}