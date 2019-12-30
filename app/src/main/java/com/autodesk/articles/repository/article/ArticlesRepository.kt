package com.autodesk.articles.repository.article

import com.autodesk.articles.data.Article
import com.autodesk.articles.util.doWorkOnBackgroundResultsOnMain
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber


class ArticlesRepository(
    private val localDataSource: ArticleLocalDataSource,
    private val remoteDataSource: ArticleRemoteDataSource
) {

    val incomingData: PublishSubject<List<Article>> = PublishSubject.create<List<Article>>()

    val disposable: CompositeDisposable = CompositeDisposable()

    // get data from local db
    fun getData(source: String) {
        disposable.add(
            localDataSource.getArticles(source)
                .doWorkOnBackgroundResultsOnMain()
                .doAfterNext {
                    refreshData(source)
                }
                .subscribe({ listArticles ->
                    incomingData.onNext(listArticles)
                }, { error ->
                    handleError(error)
                })
        )
    }

    // get data from remote - api
    fun refreshData(source: String) {
        disposable.add(
            remoteDataSource.getArticles(source)
                .doWorkOnBackgroundResultsOnMain()
                .subscribe({ data -> saveData(data.articles) },
                    { error -> handleError(error) })
        )
    }

    // save data to db
    private fun saveData(data: List<Article>) {
        localDataSource.saveArticles(data)
    }

    private fun handleError(error: Throwable) {
        Timber.e(error)
    }

    fun onClear() {
        disposable.clear()
    }


}