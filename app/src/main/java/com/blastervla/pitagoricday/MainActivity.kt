package com.blastervla.pitagoricday

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import com.blastervla.pitagoricday.Date.Month
import com.blastervla.pitagoricday.Date.Year
import com.blastervla.pitagoricday.Model.MonthData
import com.blastervla.pitagoricday.View.DayView
import com.blastervla.pitagoricday.View.MonthView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.childrenSequence


class MainActivity : AppCompatActivity() {

    private var monthView: MonthView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        monthView = MonthView(applicationContext, MonthData(Month.AUGUST, Year(2017)))
        rootLayout.addView(monthView, rootLayout.childCount - 1)
        lblCurrentMonth.text = "August, 2017"

        btnNextMonth.setOnClickListener {
            monthView!!.next()
            lblCurrentMonth.text = monthView!!.monthData.toString()
        }

        btnPrevMonth.setOnClickListener {
            monthView!!.previous()
            lblCurrentMonth.text = monthView!!.monthData.toString()
        }

        txtErrorThreshold.setOnKeyListener({_, _, _ -> updateErrorThreshold()})
    }

    private fun updateErrorThreshold(): Boolean {

        if (txtErrorThreshold.text.toString() != "" && txtErrorThreshold.text.toString().toInt() != 0) {
            DayView.errorThreshold = txtErrorThreshold.text.toString().toInt()
            monthView!!.redrawView()
        } else {
            DayView.errorThreshold = 1
        }

        updateColorParameterValues()

        return false
    }

    private fun updateColorParameterValues() {
        var i = -5
        colorParameterLayout.childrenSequence().forEach {
            val valueString = (i * DayView.errorThreshold).toString()
            (it as TextView).text = valueString
            i++
        }
    }
}
