package com.autodesk.articles.repository.article

import com.autodesk.articles.data.Article
import com.autodesk.articles.util.doWorkOnBackgroundResultsOnMain
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

class ArticlesRepository(
    private val articlesLocalDataSource: ArticlesLocalDataSource,
    private val articlesRemoteDataSource: ArticlesRemoteDataSource
) {

    val incomingData: PublishSubject<List<Article>> = PublishSubject.create<List<Article>>()

    val disposable: CompositeDisposable = CompositeDisposable()

    // get data from local db
    fun getData() {
        disposable.add(
            articlesLocalDataSource.getArticles()
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
            articlesRemoteDataSource.getArticles()
                .doWorkOnBackgroundResultsOnMain()
                .subscribe({ data -> saveData(data.articles) },
                    { error -> handleError(error) })
        )
    }

    // save data to db
    fun saveData(data: List<Article>) {
        articlesLocalDataSource.saveArticles(data)
    }

    fun handleError(error: Throwable) {
        Timber.e(error)
    }

    fun onClear() {
        disposable.clear()
    }


}