package com.blastervla.pitagoricday.Date

/**
 * Created by blastervla on 10/15/17.
 */
class Year(val number: Int) {
    fun isLeap(): Boolean {
        return ((number % 4 == 0) && (number % 100 != 0)) || (number % 400 == 0)
    }
}