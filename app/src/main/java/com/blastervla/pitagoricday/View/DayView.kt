package com.blastervla.pitagoricday.View

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.view.View
import android.widget.TextView
import com.blastervla.pitagoricday.Model.DayData
import com.blastervla.pitagoricday.R
import org.jetbrains.anko.dip
import org.jetbrains.anko.sp

/**
 * Created by blastervla on 10/12/17.
 */

class DayView(private val ctx: Context, private val dayData: DayData) : View(ctx) {
    companion object {
        val DEFAULT_WIDTH_IN_DIP = 25
        val DEFAULT_HEIGHT_IN_DIP = 25
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        this.setBackgroundColor(resources.getIntArray(R.array.dayColors)[colorIndexByPitagoricOffset(dayData.pitagoricOffset)])

        if (canvas != null) {
            val textPaint = TextPaint()
            textPaint.textSize = sp(16).toFloat()
            textPaint.color = Color.BLACK
            canvas.drawText(dayData.day.toString(), dip(4).toFloat(), dip(16).toFloat(), textPaint)
            val borderPaint = Paint()
            borderPaint.color = Color.parseColor("#FFDDDDDD")
            borderPaint.strokeWidth = dip(1).toFloat()
            canvas.drawLine(0f, 0f, this.width.toFloat(), 0f, borderPaint)
            canvas.drawLine(0f, 0f, 0f, this.height.toFloat(), borderPaint)
            canvas.drawLine(this.width.toFloat(), this.height.toFloat(), 0f, this.height.toFloat(), borderPaint)
            canvas.drawLine(this.width.toFloat(), this.height.toFloat(), this.width.toFloat(), 0f, borderPaint)
        }
    }

    private fun colorIndexByPitagoricOffset(pitagoricOffset: Int): Int {
        return if ((pitagoricOffset + 5) > 10 || pitagoricOffset + 5 < 0) 11 else (pitagoricOffset + 5)
    }


}