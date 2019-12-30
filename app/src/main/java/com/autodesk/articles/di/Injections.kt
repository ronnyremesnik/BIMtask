package com.autodesk.articles.di

import com.autodesk.articles.app.BIMApplication
import com.autodesk.articles.data.db.MyRoomDB
import com.autodesk.articles.network.APICalls
import com.autodesk.articles.network.RetrofitClient
import com.autodesk.articles.repository.article.ArticleLocalDataSource
import com.autodesk.articles.repository.article.ArticleRemoteDataSource
import com.autodesk.articles.repository.article.ArticlesRepository
import com.autodesk.articles.repository.source.SourceLocalDataSourceImpl
import com.autodesk.articles.repository.source.SourceRemoteDataSource
import com.autodesk.articles.repository.source.SourcesRepository
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

        private fun injectLocalDataSource(): ArticleLocalDataSource {
            return ArticleLocalDataSource(injectDatabase())
        }

        private fun injectRemoteDataSource(): ArticleRemoteDataSource {
            return ArticleRemoteDataSource(injectAPICalls())
        }

        private fun injectSourceLocalDataSource(): SourceLocalDataSourceImpl {
            return SourceLocalDataSourceImpl(injectDatabase())
        }

        private fun injectSourceRemoteDataSource(): SourceRemoteDataSource {
            return SourceRemoteDataSource(injectAPICalls())
        }

        fun injectArticleRepository(): ArticlesRepository {
            return ArticlesRepository(
                injectLocalDataSource(),
                injectRemoteDataSource()
            )
        }

        fun injectSourceRepository(): SourcesRepository {
            return SourcesRepository(
                injectSourceLocalDataSource(),
                injectSourceRemoteDataSource()
            )
        }
    }
}