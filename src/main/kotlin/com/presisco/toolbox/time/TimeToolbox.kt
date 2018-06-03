package com.presisco.toolbox.time

import java.text.SimpleDateFormat
import java.util.*

object TimeToolbox {
    fun getCurrentCalendar(): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        return calendar
    }

    fun getYesterdayString(format: String): String {
        val calendar = getCurrentCalendar()
        calendar.add(Calendar.DAY_OF_MONTH, -1)
        return SimpleDateFormat(format).format(calendar.time)
    }

    fun getCalendarFromString(dateString: String, format: String): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = SimpleDateFormat(format).parse(dateString).time
        return calendar
    }
}