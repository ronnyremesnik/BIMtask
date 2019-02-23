package com.yoavg.bimyoav.repository

import com.yoavg.bimyoav.data.Article
import com.yoavg.bimyoav.data.db.MyRoomDB
import com.yoavg.bimyoav.main_screen.MainScreenDataContract
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Yoav G on 23/02/2019.
 */
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