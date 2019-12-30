package com.autodesk.articles.ui.source

import com.autodesk.articles.app.Constants
import com.autodesk.articles.data.Article
import com.autodesk.articles.data.Source
import com.autodesk.articles.ui.BaseItemAdapter


class SourceAdapter(listener: SourceListener) : BaseItemAdapter<Source>(listener) {
    override fun getExtraKey() = Constants.SOURCE
}