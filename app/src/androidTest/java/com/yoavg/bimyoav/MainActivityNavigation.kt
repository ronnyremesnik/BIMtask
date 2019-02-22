package com.yoavg.bimyoav

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import com.yoavg.bimyoav.main_screen.MainActivity
import org.junit.Rule
import org.junit.Test

/**
 * Created by Yoav G on 22/02/2019.
 */
class MainActivityNavigation {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    var instantTask = InstantTaskExecutorRule()


    @Test
    fun checkItemsAfterRotation() {
        // Rotate device to landscape
        with(UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())) {
            setOrientationLeft()
        }

    }
}