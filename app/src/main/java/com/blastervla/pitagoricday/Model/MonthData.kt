package com.blastervla.pitagoricday.Model

import com.blastervla.pitagoricday.Date.Month
import com.blastervla.pitagoricday.Date.WeekDay
import com.blastervla.pitagoricday.Date.Year

/**
 * Created by blastervla on 10/15/17.
 */

class MonthData(val month: Month, val year: Year) {
    val dayAmount = month.length(year.isLeap())
    private var dayIndex = 0

    val extraCalendarWeekDays: Int
        get() {
            val firstWeekDay = DayData(0, month.ordinal + 1, year.number).weekDay
            var currWeekDay = WeekDay.SUNDAY
            return firstWeekDay.ordinal - WeekDay.SUNDAY.ordinal
        }

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

    fun nextMonth(): MonthData {
        val auxMonth = this.month.next()
        return if (auxMonth != null)
            MonthData(auxMonth, this.year)
        else
            MonthData(Month.JANUARY, this.year.next())
    }


    fun previousMonth(): MonthData {
        val auxMonth = this.month.previous()
        return if (auxMonth != null)
            MonthData(auxMonth, this.year)
        else
            MonthData(Month.DECEMBER, this.year.previous())
    }

    fun reset() {
        dayIndex = 0
    }

    override fun toString(): String {
        return month.toString() + ", " + year.toString()
    }
}