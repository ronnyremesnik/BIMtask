package com.autodesk.articles.repository

import com.autodesk.articles.app.Constants
import com.autodesk.articles.data.APIResponse
import com.autodesk.articles.main_screen.MainScreenDataContract
import com.autodesk.articles.network.APICalls
import io.reactivex.Single

class RemoteDataSource(private val calls:APICalls) : MainScreenDataContract.RemoteDataSource {

    override fun getArticles(): Single<APIResponse> {
        return calls.getNews(Constants.NEWS_API_KEY, Constants.ABC_NEWS_SOURCE)
    }
}