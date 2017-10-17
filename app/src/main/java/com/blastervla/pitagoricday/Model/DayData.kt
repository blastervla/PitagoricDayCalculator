package com.blastervla.pitagoricday.Model

import com.blastervla.pitagoricday.Date.WeekDay

/**
 * Created by blastervla on 10/15/17.
 */

class DayData(val day: Int, val month: Int, val year: Int) {
    val weekDay = WeekDay.fromDate(day, month, year)
    val pitagoricOffset: Int
        get() {
            val twoDigitYear = twoDigitYear(year)
            return (twoDigitYear * twoDigitYear) - (day * day + month * month)
        }

    private fun twoDigitYear(year: Int): Int {
        val yearString = year.toString()
        return yearString.substring(yearString.length - 2).toInt()
    }
}