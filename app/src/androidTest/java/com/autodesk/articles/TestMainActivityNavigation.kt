package com.autodesk.articles

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.autodesk.articles.main_screen.SourcesActivity
import com.autodesk.articles.ui.article.ArticleAdapter
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestMainActivityNavigation {

    private val numberOfItems = 10

    private lateinit var testIdlingResource: IdlingResource

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(SourcesActivity::class.java)

    @get:Rule
    var instantTask = InstantTaskExecutorRule()


    @Before
    fun init() {
        // get the idling resource from viewmodel so test can wait for async calls
        testIdlingResource = activityTestRule.activity.idlingResource
        IdlingRegistry.getInstance().register(testIdlingResource)
    }

    @After
    fun tearDown() {
        // remove resources once we finish
        IdlingRegistry.getInstance().unregister(testIdlingResource)
    }

    @Test
    fun test_clickItemInList() {
        // check there are items and able to click one
        onView(ViewMatchers.withId(R.id.news_rv))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ArticleAdapter.ArticleViewHolder>
                    (3, click())
            )
    }

    @Test
    fun test_checkItemsAfterRotation() {
        // Check there are 10 items in recyclerview
        assertRecyclerViewItemCount(R.id.news_rv, numberOfItems)

        // Rotate device to landscape
        with(UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())) {
            setOrientationLeft()
        }

        // Check there are still 10 items in recyclerview
        assertRecyclerViewItemCount(R.id.news_rv, numberOfItems)
    }
}