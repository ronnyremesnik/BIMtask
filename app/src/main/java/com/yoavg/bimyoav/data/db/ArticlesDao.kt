package com.yoavg.bimyoav.data.db

import androidx.room.*
import com.yoavg.bimyoav.app.Constants
import com.yoavg.bimyoav.data.Article
import io.reactivex.Flowable

/**
 * Created by Yoav G on 23/02/2019.
 */

@Dao
abstract class ArticlesDao {

    @Query("SELECT * FROM articles ORDER BY title DESC LIMIT :limit")
    protected abstract fun getAllArticles(limit: Int): Flowable<List<Article>>

    // this is to avoid false negatives on db updates
    fun getAllArticlesDistinct(): Flowable<List<Article>> =
        getAllArticles(Constants.DATA_PRESENTED_LIMIT).distinctUntilChanged()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertArticles(list: List<Article>)

    @Delete
    abstract fun deleteArticle(article: Article)

    @Query("DELETE FROM articles")
    abstract fun deleteAllArticles()


}