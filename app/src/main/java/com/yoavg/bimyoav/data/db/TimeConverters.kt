package com.yoavg.bimyoav.data.db

import androidx.room.TypeConverter
import com.yoavg.bimyoav.app.Constants
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TimeConverters {

    @TypeConverter
    fun originalDateStringToTimeInMillis(dateString: String): Long {
        val originalTimeFormat = SimpleDateFormat(Constants.ORIGINAL_DATE_FORMAT, Locale.US)
        originalTimeFormat.timeZone = TimeZone.getTimeZone("UTC")
        var date: Date? = null
        try {
            date = originalTimeFormat.parse(dateString)
        } catch (e: ParseException) {
            Timber.e(e)
        }
        return date?.time ?: 0
    }

    @TypeConverter
    fun timeInMillsToDesiredDateString(time: Long): String {
        val date = Date(time)
        val outputFormat = SimpleDateFormat(Constants.DESIRED_DATE_FORMAT, Locale.US)
        outputFormat.timeZone = TimeZone.getTimeZone("UTC")
        var resolvedDate: String? = null
        try {
            resolvedDate = outputFormat.format(date)
        } catch (e: ParseException) {
            Timber.e(e)
        }
        return resolvedDate ?: ""

    }
}