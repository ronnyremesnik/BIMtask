package com.autodesk.articles.repository.article

import com.autodesk.articles.data.Article
import com.autodesk.articles.data.db.MyRoomDB
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class ArticleLocalDataSource(private val roomDB: MyRoomDB) {

    fun getArticles(source : String): Flowable<List<Article>> {
        return roomDB.getArticleDao().getAllArticlesDistinct(source)
    }

    fun saveArticles(data: List<Article>) {
        Completable.fromAction {
            roomDB.getArticleDao().insertArticles(data)
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }
}