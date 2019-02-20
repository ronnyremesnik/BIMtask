package com.yoavg.bimyoav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yoavg.bimyoav.data.Article

class MainScreenViewModel : ViewModel() {


    var articlesList : MutableLiveData<List<Article>>? = null


    fun getArticlesList() : LiveData<List<Article>>{
        return articlesList!!
    }

}