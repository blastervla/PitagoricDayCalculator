package com.blastervla.pitagoricday.View

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.blastervla.pitagoricday.Date.WeekDay
import com.blastervla.pitagoricday.Model.DayData
import com.blastervla.pitagoricday.Model.MonthData
import org.jetbrains.anko.dip
import org.jetbrains.anko.sp


/**
 * Created by blastervla on 10/12/17.
 */

class MonthView(val ctx: Context, var monthData: MonthData) : LinearLayout(ctx) {
    private var gridLayout: GridLayout? = null
    private var screenSize: Point = Point()

    init {
        this.orientation = VERTICAL
        fillView()
    }

    private fun fillView() {
        clearView()

        var dayData: DayData? = monthData.nextDay()
        var currentDay = WeekDay.SUNDAY

        while (dayData!!.weekDay < currentDay) {
            gridLayout!!.addView(emptyView())
            currentDay = currentDay.nextDay()
        }

        var i = 7
        var c = 0
        var r = 1

        while (dayData != null) {
            if (c == gridLayout!!.columnCount) {
                c = 0
                r++
            }

            val toAdd = DayView(ctx, dayData)
            gridLayout!!.addView(toAdd, i)
            val params = GridLayout.LayoutParams()

            params.width = screenSize.x / 7
            params.height = params.width
            params.columnSpec = GridLayout.spec(c)
            params.rowSpec = GridLayout.spec(r)

            toAdd.layoutParams = params

            dayData = monthData.nextDay()
            i++
            c++
        }
        this.addView(gridLayout)
    }

    private fun emptyView(): View {
        val toReturn = View(ctx)
        toReturn.layoutParams.height = dip(DayView.DEFAULT_HEIGHT_IN_DIP)
        (toReturn.layoutParams as (LinearLayout.LayoutParams)).weight = 1f
        toReturn.setBackgroundColor(Color.WHITE)
        return toReturn
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
        this.minimumHeight = this.height
        this.minimumWidth = this.height
        this.removeAllViews()
        gridLayout = GridLayout(ctx)
        gridLayout!!.columnCount = 7
        gridLayout!!.alignmentMode = GridLayout.ALIGN_BOUNDS
        val days = ((monthData.dayAmount + monthData.extraCalendarWeekDays))
        gridLayout!!.rowCount = if (days % 7 == 0) days / 7 + 1 else days / 7 + 2

        addWeekDayLabels()
    }

    private fun addWeekDayLabels() {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        display.getSize(screenSize)

        var i = 0
        WeekDay.abbreviatedStrings(context).forEach {
            val lblWeekDay = TextView(ctx)
            lblWeekDay.text = it
            lblWeekDay.textSize = sp(7).toFloat()
            gridLayout!!.addView(lblWeekDay, i)

            val params = GridLayout.LayoutParams()
            params.width = screenSize.x / 7
            params.height = dip(25)
            params.setGravity(Gravity.CENTER)
            params.rowSpec = GridLayout.spec(0)
            params.columnSpec = GridLayout.spec(i)

            lblWeekDay.layoutParams = params

            i++
        }
    }

    fun redrawView() {
        monthData.reset()
        fillView()
    }
}