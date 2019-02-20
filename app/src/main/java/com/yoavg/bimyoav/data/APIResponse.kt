package com.yoavg.bimyoav.data

import com.google.gson.annotations.SerializedName

class APIResponse(@SerializedName("articles") val articles :List<Article>)