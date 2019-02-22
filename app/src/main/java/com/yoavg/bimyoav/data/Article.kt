package com.yoavg.bimyoav.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article (
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("publishedAt") val timeLine: String,
    @SerializedName("description") val description: String,
    @SerializedName("urlToImage") val imgUrl: String
) : Parcelable