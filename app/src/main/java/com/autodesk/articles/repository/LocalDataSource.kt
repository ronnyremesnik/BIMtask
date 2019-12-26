package com.autodesk.articles.repository

import com.autodesk.articles.data.Article
import com.autodesk.articles.data.db.MyRoomDB
import com.autodesk.articles.main_screen.MainScreenDataContract
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class LocalDataSource(private val roomDB: MyRoomDB) : MainScreenDataContract.LocalDataSource {

    override fun getArticles(): Flowable<List<Article>> {
        return roomDB.getArticleDao().getAllArticlesDistinct()
    }

    override fun saveArticles(data: List<Article>) {
        Completable.fromAction {
            roomDB.getArticleDao().insertArticles(data)
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }
}