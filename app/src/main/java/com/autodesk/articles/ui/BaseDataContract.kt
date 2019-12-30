package com.autodesk.articles.ui

import androidx.test.espresso.IdlingResource
import com.autodesk.articles.data.ArticlesResponse
import com.autodesk.articles.data.Article
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

interface BaseDataContract {

    interface BaseRepository<Entity> {
        val incomingData: PublishSubject<List<Entity>>
        val disposable: CompositeDisposable
        fun getData()
        fun refreshData()
        fun saveData(data: List<Entity>)
        fun handleError(error: Throwable)
        fun onClear()
    }

    interface View {
        val idlingResource: IdlingResource
    }

    interface ViewModel {
        fun getEntitiesList()
    }

    interface BaseLocalDataSource<Entity> {
        fun getEntities(): Flowable<List<Entity>>
        fun saveEntities(data: List<Entity>)
    }

//    interface BaseRemoteDataSource<EntityResponse> {
//        fun getArticles(source: String): Single<EntityResponse>?
//        fun getSources(): Single<EntityResponse>?
//    }
}