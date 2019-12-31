package com.autodesk.articles.repository.article

import com.autodesk.articles.app.Constants
import com.autodesk.articles.data.ArticlesResponse
import com.autodesk.articles.network.APICalls
import io.reactivex.Single

class ArticlesRemoteDataSource(private val calls: APICalls) {

    fun getArticles(): Single<ArticlesResponse> {
        return calls.getNews(Constants.NEWS_API_KEY, Constants.ABC_NEWS_SOURCE)
    }
}