package com.autodesk.articles.app

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class BIMApplication : Application() {

    init {
        instance = this
    }

    companion object {

        private var instance: BIMApplication? = null
        const val ALLOW_LEAK_CANARY = false

        fun applicationContext(): BIMApplication {
            return instance as BIMApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())

        // turned off at the moment because it brings false positives (GC was being lazy messages)
        if (ALLOW_LEAK_CANARY) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                return
            }
            LeakCanary.install(this)
        }

    }
}
