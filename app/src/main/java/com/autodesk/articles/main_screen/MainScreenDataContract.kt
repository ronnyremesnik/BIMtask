package com.autodesk.articles.main_screen

import androidx.test.espresso.IdlingResource
import com.autodesk.articles.data.APIResponse
import com.autodesk.articles.data.Article
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

interface MainScreenDataContract {

    interface Repository {
        val incomingData : PublishSubject<List<Article>>
        val disposable : CompositeDisposable
        fun getData()
        fun refreshData()
        fun saveData(data: List<Article>)
        fun handleError(error: Throwable)
        fun onClear()
    }

    interface View {
        val idlingResource: IdlingResource
    }

    interface ViewModel {
        fun getArticlesList()
    }

    interface LocalDataSource {
        fun getArticles(): Flowable<List<Article>>
        fun saveArticles(data: List<Article>)
    }

    interface RemoteDataSource {
        fun getArticles(): Single<APIResponse>
    }
}