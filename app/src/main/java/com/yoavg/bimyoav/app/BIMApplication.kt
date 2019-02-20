package com.yoavg.bimyoav.app

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class BIMApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

}