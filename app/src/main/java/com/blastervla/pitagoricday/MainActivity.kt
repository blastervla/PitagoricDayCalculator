package com.blastervla.pitagoricday

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import com.blastervla.pitagoricday.Date.Month
import com.blastervla.pitagoricday.Date.Year
import com.blastervla.pitagoricday.Model.MonthData
import com.blastervla.pitagoricday.View.DayView
import com.blastervla.pitagoricday.View.MonthView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*


class MainActivity : AppCompatActivity() {

    private var monthView: MonthView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        monthView = MonthView(applicationContext, MonthData(Month.AUGUST, Year(2017)))
        rootLayout.addView(monthView, 1)
        lblCurrentMonth.text = getString(R.string.temp_string_starting_month)

        btnNextMonth.setOnClickListener {
            monthView!!.next()
            lblCurrentMonth.text = monthView!!.monthData.toString(applicationContext)
        }

        btnPrevMonth.setOnClickListener {
            monthView!!.previous()
            lblCurrentMonth.text = monthView!!.monthData.toString(applicationContext)
        }

        txtErrorThreshold.setOnKeyListener({ _, _, _ -> updateErrorThreshold() })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)
        if (item != null && item.itemId == R.id.btnCredits)
            showCredits()
        return true
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

    private fun showCredits() {
        alert {
            title = applicationContext.resources.getString(R.string.credits)

            customView {
                linearLayout {
                    padding = dip(25)
                    orientation = LinearLayout.VERTICAL
                    textView {
                        text = context.getString(R.string.credits_developed_by)
                        textSize = sp(7).toFloat()
                        setTypeface(typeface, Typeface.BOLD)
                    }
                    space {
                        minimumHeight = dip(5)
                    }
                    textView {
                        text = context.getString(R.string.credits_developer)
                        textSize = sp(6.5f).toFloat()
                    }
                }
            }

            yesButton {
                it.dismiss()
            }
        }.show()


    }
}
