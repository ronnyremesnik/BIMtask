package com.autodesk.articles.di

import com.autodesk.articles.app.BIMApplication
import com.autodesk.articles.data.db.MyRoomDB
import com.autodesk.articles.network.APICalls
import com.autodesk.articles.network.RetrofitClient
import com.autodesk.articles.repository.*
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

        private fun injectLocalDataSource(): ArticleLocalDataSourceImpl {
            return ArticleLocalDataSourceImpl(injectDatabase())
        }

        private fun injectRemoteDataSource(): ArticleRemoteDataSourceImpl {
            return ArticleRemoteDataSourceImpl(injectAPICalls())
        }

        private fun injectSourceLocalDataSource(): SourceLocalDataSourceImpl {
            return SourceLocalDataSourceImpl(injectDatabase())
        }

        private fun injectSourceRemoteDataSource(): SourceRemoteDataSourceImpl {
            return SourceRemoteDataSourceImpl(injectAPICalls())
        }

        fun injectArticleRepository(): ArticlesRepository {
            return ArticlesRepository(injectLocalDataSource(), injectRemoteDataSource())
        }

        fun injectSourceRepository(): SourcesRepository {
            return SourcesRepository(injectSourceLocalDataSource(), injectSourceRemoteDataSource())
        }
    }
}