package com.blastervla.pitagoricday.Model

import com.blastervla.pitagoricday.Date.Month
import com.blastervla.pitagoricday.Date.Year

/**
 * Created by blastervla on 10/15/17.
 */

class MonthData(val month: Month, val year: Year) {
    private val dayAmount = month.length(year.isLeap())
    private var dayIndex = 0

    /**
     * Returns the next day of the month. May return null if there is no next day available.
     */
    fun nextDay(): DayData? {
        if (dayIndex < dayAmount) {
            dayIndex++
            return DayData(dayIndex, month.ordinal + 1, year.number)
        }
        return null
    }
}