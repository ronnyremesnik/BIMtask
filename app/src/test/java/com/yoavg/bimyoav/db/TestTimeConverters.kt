package com.yoavg.bimyoav.db

import com.yoavg.bimyoav.app.Constants
import com.yoavg.bimyoav.data.db.TimeConverters
import junit.framework.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class TestTimeConverters {

    private val sampleDateString = "2019-02-23T17:18:14Z"
    private val sampleTimeInMillis = 1550942294001
    private val converters = TimeConverters()

    @Test
    fun test_StringToMillis() {
        val originalTimeFormat = SimpleDateFormat(Constants.ORIGINAL_DATE_FORMAT, Locale.US)
        originalTimeFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = originalTimeFormat.parse(sampleDateString)
        assertEquals(date.time, converters.originalDateStringToTimeInMillis(sampleDateString))
        assertNotEquals(date.time + 100, converters.originalDateStringToTimeInMillis(sampleDateString))
    }

    @Test
    fun test_millisToDateString() {
        val date = Date(sampleTimeInMillis)
        val outputFormat = SimpleDateFormat(Constants.DESIRED_DATE_FORMAT, Locale.US)
        outputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val resolvedDate = outputFormat.format(date)
        assertEquals(resolvedDate, converters.timeInMillsToDesiredDateString(sampleTimeInMillis))
        assertNotEquals(resolvedDate, converters.timeInMillsToDesiredDateString(sampleTimeInMillis + 10000000))
    }
}