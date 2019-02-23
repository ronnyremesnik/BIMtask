package com.yoavg.bimyoav.app

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class BIMApplication : Application() {

    init {
        instance = this
    }

    companion object {

        private var instance: BIMApplication? = null

        fun applicationContext(): BIMApplication {
            return instance as BIMApplication
        }
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        // LeakCanary.install(this)
    }


}
