package com.autodesk.articles.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.autodesk.articles.app.Constants
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Constants.TABLE_NAME_SOURCES)
data class Source(
    @SerializedName("id") @PrimaryKey @ColumnInfo val id: String,
    @SerializedName("name") @ColumnInfo val name: String
) : Displayable {
    override fun getItemId(): String = id

    override fun getImageUrl(): Nothing? = null

    override fun getItemTitle() = name

    override fun getItemDescription(): String? = null
}