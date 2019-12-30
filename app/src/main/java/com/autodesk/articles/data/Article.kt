package com.autodesk.articles.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.autodesk.articles.app.Constants
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Constants.TABLE_NAME_ARTICLES)
data class Article(
    @SerializedName("title") @PrimaryKey @ColumnInfo val title: String,
    @SerializedName("url") @ColumnInfo val url: String,
    @SerializedName("publishedAt") @ColumnInfo val timeLine: String?,
    @SerializedName("description") @ColumnInfo val description: String?,
    @SerializedName("urlToImage") @ColumnInfo val imgUrl: String?,
    @Embedded @SerializedName("source") val source: ArticleSource
) : Displayable {
    override fun getItemId(): String = url

    override fun getImageUrl() = imgUrl

    override fun getItemTitle() = title

    override fun getItemDescription() = description
}

@Parcelize
data class ArticleSource(
    @SerializedName("id") @ColumnInfo val sourceId: String,
    @SerializedName("name") @ColumnInfo val sourceName: String
) : Parcelable