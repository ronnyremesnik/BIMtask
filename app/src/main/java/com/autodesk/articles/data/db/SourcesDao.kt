package com.autodesk.articles.data.db

import androidx.room.*
import com.autodesk.articles.app.Constants
import com.autodesk.articles.data.Article
import com.autodesk.articles.data.Source
import io.reactivex.Flowable

@Dao
abstract class SourcesDao {

    @Query("SELECT * FROM sources LIMIT :limit")
    protected abstract fun getAllSources(limit: Int): Flowable<List<Source>>

    // this is to avoid false negatives on db updates
    fun getAllSourcesDistinct(): Flowable<List<Source>> =
        getAllSources(Constants.DATA_PRESENTED_LIMIT).distinctUntilChanged()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSources(list: List<Source>)

    @Delete
    abstract fun deleteSource(article: Source)

    @Query("DELETE FROM sources")
    abstract fun deleteAllSources()


}