package com.blastervla.pitagoricday.View

import android.content.Context
import android.graphics.Canvas
import android.view.View
import android.widget.LinearLayout
import com.blastervla.pitagoricday.R
import org.jetbrains.anko.dip

/**
 * Created by blastervla on 10/12/17.
 */

class DayView(private val ctx: Context, private val pitagoricOffset: Int) : View(ctx) {
    companion object {
        val DEFAULT_WIDTH_IN_DIP = 25
        val DEFAULT_HEIGHT_IN_DIP = 25
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        this.setBackgroundColor(resources.getIntArray(R.array.dayColors)[colorIndexByPitagoricOffset(pitagoricOffset)])
        (this.layoutParams as (LinearLayout.LayoutParams)).weight = 1f
        this.layoutParams.height = dip(DEFAULT_HEIGHT_IN_DIP)
    }

    private fun colorIndexByPitagoricOffset(pitagoricOffset: Int): Int {
        return if((pitagoricOffset + 5) > 10 || pitagoricOffset + 5 < 0) 11 else (pitagoricOffset + 5)
    }


}