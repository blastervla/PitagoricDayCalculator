package com.blastervla.pitagoricday

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.blastervla.pitagoricday.Date.Month
import com.blastervla.pitagoricday.Date.Year
import com.blastervla.pitagoricday.Model.MonthData
import com.blastervla.pitagoricday.View.MonthView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var monthView: MonthView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        monthView = MonthView(applicationContext, MonthData(Month.AUGUST, Year(2017)))
        rootLayout.addView(monthView)
    }
}
