package com.yoavg.bimyoav.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.yoavg.bimyoav.app.Constants
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Constants.TABLE_NAME)
data class Article(
    @SerializedName("title") @PrimaryKey @ColumnInfo val title: String,
    @SerializedName("url") @ColumnInfo val url: String,
    @SerializedName("publishedAt") @ColumnInfo val timeLine: String?,
    @SerializedName("description") @ColumnInfo val description: String?,
    @SerializedName("urlToImage") @ColumnInfo val imgUrl: String?
) : Parcelable