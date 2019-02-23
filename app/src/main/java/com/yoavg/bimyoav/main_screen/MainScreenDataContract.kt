package com.yoavg.bimyoav.main_screen

import androidx.test.espresso.IdlingResource
import com.yoavg.bimyoav.data.APIResponse
import io.reactivex.Single

/**
 * Created by Yoav G on 22/02/2019.
 */
interface MainScreenDataContract {

    interface Repository {

        fun getData(source: String): Single<APIResponse>
    }

    interface ViewModel {

        val idlingResource : IdlingResource
        fun getArticlesList(source: String)

    }
}