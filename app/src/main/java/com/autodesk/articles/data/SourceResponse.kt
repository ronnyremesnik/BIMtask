package com.autodesk.articles.data

import com.google.gson.annotations.SerializedName

class SourceResponse(
    @SerializedName("sources") val sources: List<Source>,
    @SerializedName("status") val status : String
) : BaseResponse()