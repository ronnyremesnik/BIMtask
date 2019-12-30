package com.autodesk.articles.repository.article

import com.autodesk.articles.app.Constants
import com.autodesk.articles.data.ArticlesResponse
import com.autodesk.articles.network.APICalls
import io.reactivex.Single

class ArticleRemoteDataSource(private val calls:APICalls) {

    fun getArticles(source: String): Single<ArticlesResponse> {
        return calls.getNews(Constants.NEWS_API_KEY, source)
    }

}