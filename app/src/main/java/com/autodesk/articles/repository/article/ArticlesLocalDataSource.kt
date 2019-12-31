package com.autodesk.articles.repository.article

import com.autodesk.articles.data.Article
import com.autodesk.articles.data.db.MyRoomDB
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class ArticlesLocalDataSource(private val roomDB: MyRoomDB) {

    fun getArticles(): Flowable<List<Article>> {
        return roomDB.getArticleDao().getAllArticlesDistinct()
    }

    fun saveArticles(data: List<Article>) {
        Completable.fromAction {
            roomDB.getArticleDao().insertArticles(data)
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }
}