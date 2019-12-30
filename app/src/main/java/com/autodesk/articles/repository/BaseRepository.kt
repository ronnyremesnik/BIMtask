package com.autodesk.articles.repository

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

abstract class BaseRepository<Entity> {

    val incomingData: PublishSubject<List<Entity>> = PublishSubject.create<List<Entity>>()

    val disposable: CompositeDisposable = CompositeDisposable()

    fun handleError(error: Throwable) {
        Timber.e(error)
    }
}