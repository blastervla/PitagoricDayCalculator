package com.blastervla.pitagoricday.Date

/**
 * Created by blastervla on 10/15/17.
 */
class Year(val number: Int) {
    fun isLeap(): Boolean {
        return ((number % 4 == 0) && (number % 100 != 0)) || (number % 400 == 0)
    }

    fun next(): Year {
        return Year(number + 1)
    }

    fun previous(): Year {
        return Year(number - 1)
    }

    override fun toString(): String {
        return number.toString()
    }
}