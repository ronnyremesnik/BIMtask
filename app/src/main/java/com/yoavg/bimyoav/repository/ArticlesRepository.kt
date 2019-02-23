package com.yoavg.bimyoav.repository

import com.yoavg.bimyoav.data.Article
import com.yoavg.bimyoav.main_screen.MainScreenDataContract
import com.yoavg.bimyoav.util.doWorkOnBackgroundResultsOnMain
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class ArticlesRepository(
    private val localDataSource: MainScreenDataContract.LocalDataSource,
    private val remoteDataSource: MainScreenDataContract.RemoteDataSource
) : MainScreenDataContract.Repository {

    override val incomingData: PublishSubject<List<Article>> = PublishSubject.create<List<Article>>()

    override val disposable: CompositeDisposable = CompositeDisposable()

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

    override fun refreshData() {
        disposable.add(
            remoteDataSource.getArticles()
                .doWorkOnBackgroundResultsOnMain()
                .subscribe({ data -> saveData(data.articles) },
                    { error -> handleError(error) })
        )
    }

    override fun saveData(data: List<Article>) {
        localDataSource.saveArticles(data)
    }

    override fun handleError(error: Throwable) {
    }

    override fun onClear() {
        disposable.clear()
    }


}