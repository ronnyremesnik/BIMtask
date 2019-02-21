package com.yoavg.bimyoav.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Article (
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("publishedAt") val timeLine: String,
    @SerializedName("description") val description: String,
    @SerializedName("urlToImage") val imgUrl: String
) : Serializable