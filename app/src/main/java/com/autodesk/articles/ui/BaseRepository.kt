package com.autodesk.articles.ui

import com.autodesk.articles.util.doWorkOnBackgroundResultsOnMain
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

abstract class BaseRepository<Entity, EntityResponse>(
    protected val localDataSource: BaseDataContract.BaseLocalDataSource<Entity>
//    protected val remoteDataSource: BaseDataContract.BaseRemoteDataSource<EntityResponse>
) : BaseDataContract.BaseRepository<Entity> {

    override val incomingData: PublishSubject<List<Entity>> = PublishSubject.create<List<Entity>>()

    override val disposable: CompositeDisposable = CompositeDisposable()

    // get data from local db
    override fun getData() {
        disposable.add(
//            localDataSource.getEntities()
            getLocalEntities()
                .doWorkOnBackgroundResultsOnMain()
                .doAfterNext {
                    refreshData()
                }
                .subscribe({ listEntities ->
                    incomingData.onNext(listEntities)
                }, { error ->
                    handleError(error)
                })
        )
    }

    abstract fun getLocalEntities(): Flowable<List<Entity>>
    abstract fun getRemoteEntities(): Single<EntityResponse>
    abstract fun saveLocalData(response: EntityResponse)

    // get data from remote - api
    override fun refreshData() {
        getRemoteEntities()?.doWorkOnBackgroundResultsOnMain()
    //                .subscribe({ data -> saveData(data.articles) },
            ?.subscribe({ data -> saveLocalData(data) },
                { error -> handleError(error) })?.let {
                disposable.add(
    //            remoteDataSource.getEntities()
                    it
            )
            }
    }

    // save data to db
//    override fun saveData(data: List<Article>) {
//        localDataSource.saveEntities(data)
//    }

    override fun handleError(error: Throwable) {
        Timber.e(error)
    }

    override fun onClear() {
        disposable.clear()
    }


}