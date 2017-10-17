package com.blastervla.pitagoricday.View

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import com.blastervla.pitagoricday.Date.Month
import com.blastervla.pitagoricday.Date.WeekDay
import com.blastervla.pitagoricday.Model.DayData
import com.blastervla.pitagoricday.Model.MonthData
import org.jetbrains.anko.childrenSequence
import org.jetbrains.anko.dip

/**
 * Created by blastervla on 10/12/17.
 */

class MonthView(val ctx: Context, var monthData: MonthData) : LinearLayout(ctx) {
    private var linearLayouts: ArrayList<LinearLayout> = ArrayList()

    init {
        this.orientation = VERTICAL
        fillView()
    }

    private fun fillView() {
        clearView()

        var dayData: DayData? = monthData.nextDay()
        var currentDay = WeekDay.SUNDAY

        addLinearLayout()

        while (dayData!!.weekDay < currentDay) {
            linearLayouts.last().addView(emptyView())
            currentDay = currentDay.nextDay()
        }

        while (dayData != null) {
            linearLayouts.last().addView(DayView(ctx, dayData.pitagoricOffset))

            dayData = monthData.nextDay()
            if (dayData != null) {
                if (dayData.weekDay == WeekDay.SUNDAY) {
                    this.addView(linearLayouts.last())
                    addLinearLayout()
                }

            }
        }
        this.addView(linearLayouts.last())
    }

    private fun emptyView(): View {
        val toReturn = View(ctx)
        toReturn.layoutParams.height = dip(DayView.DEFAULT_HEIGHT_IN_DIP)
        (toReturn.layoutParams as (LinearLayout.LayoutParams)).weight = 1f
        toReturn.setBackgroundColor(Color.WHITE)
        return toReturn
    }

    private fun addLinearLayout() {
        linearLayouts.add(LinearLayout(ctx))
        linearLayouts.last().orientation = HORIZONTAL
    }

    fun next() {
        this.monthData = monthData.nextMonth()
        fillView()
    }

    fun previous() {
        this.monthData = monthData.previousMonth()
        fillView()
    }

    private fun clearView() {
        this.removeAllViews()
        this.linearLayouts = ArrayList()
    }
}