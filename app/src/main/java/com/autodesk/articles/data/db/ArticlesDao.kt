package com.autodesk.articles.data.db

import androidx.room.*
import com.autodesk.articles.app.Constants
import com.autodesk.articles.data.Article
import io.reactivex.Flowable

@Dao
abstract class ArticlesDao {

    @Query("SELECT * FROM articles WHERE sourceId = :source ORDER BY timeLine DESC LIMIT :limit")
    protected abstract fun getAllArticles(source: String, limit: Int): Flowable<List<Article>>

    // this is to avoid false negatives on db updates
    fun getAllArticlesDistinct(source: String): Flowable<List<Article>> =
        getAllArticles(source, Constants.DATA_PRESENTED_LIMIT).distinctUntilChanged()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertArticles(list: List<Article>)

    @Delete
    abstract fun deleteArticle(article: Article)

    @Query("DELETE FROM articles")
    abstract fun deleteAllArticles()


}