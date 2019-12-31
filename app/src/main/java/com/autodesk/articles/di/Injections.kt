package com.autodesk.articles.di

import com.autodesk.articles.app.BIMApplication
import com.autodesk.articles.data.db.MyRoomDB
import com.autodesk.articles.network.APICalls
import com.autodesk.articles.network.RetrofitClient
import com.autodesk.articles.repository.article.ArticlesRepository
import com.autodesk.articles.repository.article.ArticlesLocalDataSource
import com.autodesk.articles.repository.article.ArticlesRemoteDataSource
import io.reactivex.disposables.CompositeDisposable


class Injections {

    companion object {

        fun injectCompositeDisposable(): CompositeDisposable {
            return CompositeDisposable()
        }

        private fun injectAPICalls(): APICalls {
            return RetrofitClient.newsRetrofit.create(APICalls::class.java)
        }

        private fun injectDatabase(): MyRoomDB {
            return MyRoomDB.getInstance(BIMApplication.applicationContext())
        }

        private fun injectLocalDataSource(): ArticlesLocalDataSource {
            return ArticlesLocalDataSource(injectDatabase())
        }

        private fun injectRemoteDataSource(): ArticlesRemoteDataSource {
            return ArticlesRemoteDataSource(injectAPICalls())
        }

        fun injectRepository(): ArticlesRepository {
            return ArticlesRepository(
                injectLocalDataSource(),
                injectRemoteDataSource()
            )
        }
    }
}