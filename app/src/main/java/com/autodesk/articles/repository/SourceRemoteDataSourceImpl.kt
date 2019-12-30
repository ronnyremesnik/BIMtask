package com.autodesk.articles.repository

import com.autodesk.articles.app.Constants
import com.autodesk.articles.data.ArticlesResponse
import com.autodesk.articles.data.SourceResponse
import com.autodesk.articles.network.APICalls
import io.reactivex.Single

class SourceRemoteDataSourceImpl(private val calls: APICalls) {
    fun getSources(): Single<SourceResponse> {
        return calls.getSources(Constants.NEWS_API_KEY)
    }

}