package com.blastervla.pitagoricday.Date

/**
 * Created by blastervla on 10/15/17.
 */

enum class Month(i: Int) {
    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    fun length(isLeap: Boolean): Int {
        return when (this) {
            APRIL,
            JUNE,
            SEPTEMBER,
            NOVEMBER -> 30

            FEBRUARY -> if (isLeap) 29 else 28

            else -> 31
        }
    }

    /**
     * Returns the next month. Returns null if there is no next month (A.K.A when current month is
     * December)
     */
    fun next(): Month? {
        return if (this.ordinal < 12) Month.values()[this.ordinal + 1] else null
    }

    /**
     * Returns the previous month. Returns null if there is no previous month (A.K.A when current
     * month is January)
     */
    fun previous(): Month? {
        return if (this.ordinal > 1) Month.values()[this.ordinal - 1] else null
    }

    override fun toString(): String {
        return this.name.toLowerCase().capitalize()
    }
}