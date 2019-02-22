package com.yoavg.bimyoav

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import com.yoavg.bimyoav.main_screen.MainActivity
import org.hamcrest.CoreMatchers.anything
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
    fun clickItemTest(){
        val ir = activityTestRule.activity.viewModel.cid
        IdlingRegistry.getInstance().register(ir)
        onView(ViewMatchers.withId(R.id.news_rv))
            .perform(RecyclerViewActions.actionOnItemAtPosition<NewsAdapter.ArticleViewHolder>
                (3, click()))
        IdlingRegistry.getInstance().unregister(ir)
    }

    @Test
    fun checkItemsAfterRotation() {
        // Rotate device to landscape
        with(UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())) {
            setOrientationLeft()
        }

    }
}