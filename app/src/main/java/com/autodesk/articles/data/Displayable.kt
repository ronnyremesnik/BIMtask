package com.autodesk.articles.data

import android.os.Parcelable

interface Displayable : Parcelable {
    fun getItemId() : String
    fun getItemTitle() : String
    fun getItemDescription() : String?
    fun getImageUrl() : String?

}