package com.autodesk.articles.repository

import com.autodesk.articles.data.Source
import com.autodesk.articles.data.db.MyRoomDB
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class SourceLocalDataSourceImpl(private val roomDB: MyRoomDB) {

    fun getSources(): Flowable<List<Source>> {
        return roomDB.getSourceDao().getAllSourcesDistinct()
    }

    fun saveSources(data: List<Source>) {
        Completable.fromAction {
            roomDB.getSourceDao().insertSources(data)
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }
}