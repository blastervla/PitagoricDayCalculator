package com.blastervla.pitagoricday.Date

/**
 * Created by blastervla on 10/15/17.
 */
enum class WeekDay(i: Int) {
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7);

    fun nextDay(): WeekDay {
        var newDayInt: Int = 0
        newDayInt = if (this == SATURDAY) 1 else (this.ordinal + 1)
        return WeekDay.values()[newDayInt]
    }

    companion object {
        fun fromDate(day: Int, month: Int, year: Int): WeekDay {
            val dayOfWeek: Int
            val c: Int
            val y: Int
            val m: Int = 26 * (month + 1) / 10
            val d: Int = day
            val cc: Int = year / 100
            val yy: Int = year - year / 100 * 100

            c = cc / 4 - 2 * cc - 1
            y = 5 * yy / 4

            dayOfWeek = (c + y + m + d) % 7

            return when (dayOfWeek) {
                1 -> MONDAY
                2 -> TUESDAY
                3 -> WEDNESDAY
                4 -> THURSDAY
                5 -> FRIDAY
                6 -> SATURDAY
                else -> SUNDAY
            }
        }

        fun abbreviatedStrings(): Array<String>{
            return arrayOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")
        }
    }
}