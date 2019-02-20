package com.yoavg.bimyoav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yoavg.bimyoav.data.Article
import com.yoavg.bimyoav.repository.ArticlesRepository

class MainScreenViewModel : ViewModel() {


    var articlesList = MutableLiveData<List<Article>>()
    private val repo : ArticlesRepository = ArticlesRepository()

    fun getArticlesList(){
        repo.refreshData(object : CallListener{
            override fun getListData(list: List<Article>) {
                articlesList.postValue(list)
            }
        })
    }


    interface CallListener {

        fun getListData(list : List<Article>)
    }

}