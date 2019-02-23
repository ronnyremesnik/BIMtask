package com.yoavg.bimyoav.data.db

import androidx.room.*
import com.yoavg.bimyoav.data.Article
import io.reactivex.Flowable

/**
 * Created by Yoav G on 23/02/2019.
 */

@Dao
abstract class ArticlesDao {

    @Query("SELECT * FROM articles ORDER BY title DESC")
    protected abstract fun getAllArticles(): Flowable<List<Article>>

    fun getAllArticlesDistinct(): Flowable<List<Article>> =
        getAllArticles().distinctUntilChanged()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertArticles(list: List<Article>)

    @Delete
    abstract fun deleteArticle(article: Article)

    @Query("DELETE FROM articles")
    abstract fun deleteAllArticles()


}