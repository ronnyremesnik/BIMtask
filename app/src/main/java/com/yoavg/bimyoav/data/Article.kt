package com.yoavg.bimyoav.data

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("publishedAt") val timeLine: String,
    @SerializedName("description") val description: String,
    @SerializedName("urlToImage") val imgUrl: String
)