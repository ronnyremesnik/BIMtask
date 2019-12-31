package com.autodesk.articles.data

import com.google.gson.annotations.SerializedName

class ArticlesResponse(@SerializedName("articles") val articles :List<Article>)