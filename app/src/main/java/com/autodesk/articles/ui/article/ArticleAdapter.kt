package com.autodesk.articles.ui.article

import com.autodesk.articles.app.Constants
import com.autodesk.articles.data.Article
import com.autodesk.articles.ui.BaseItemAdapter


class ArticleAdapter(listener: ArticleListener) : BaseItemAdapter<Article>(listener) {
    override fun getExtraKey() = Constants.ARTICLE
}