package com.blastervla.pitagoricday.Date

import android.content.Context
import com.blastervla.pitagoricday.R

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
        return if (this.ordinal < 11) Month.values()[this.ordinal + 1] else null
    }

    /**
     * Returns the previous month. Returns null if there is no previous month (A.K.A when current
     * month is January)
     */
    fun previous(): Month? {
        return if (this.ordinal > 0) Month.values()[this.ordinal - 1] else null
    }

    override fun toString(): String {
        return super.toString()
    }

    fun toString(context: Context): String {
        return when(this) {
            JANUARY -> context.resources.getString(R.string.month_jan)
            FEBRUARY -> context.resources.getString(R.string.month_feb)
            MARCH -> context.resources.getString(R.string.month_mar)
            APRIL -> context.resources.getString(R.string.month_apr)
            MAY -> context.resources.getString(R.string.month_may)
            JUNE -> context.resources.getString(R.string.month_jun)
            JULY -> context.resources.getString(R.string.month_jul)
            AUGUST -> context.resources.getString(R.string.month_aug)
            SEPTEMBER -> context.resources.getString(R.string.month_sep)
            OCTOBER -> context.resources.getString(R.string.month_oct)
            NOVEMBER -> context.resources.getString(R.string.month_nov)
            DECEMBER -> context.resources.getString(R.string.month_dec)
        }
    }
}