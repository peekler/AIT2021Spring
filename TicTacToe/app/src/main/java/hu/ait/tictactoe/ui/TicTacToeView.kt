package hu.ait.tictactoe.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TicTacToeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var paintBackGround: Paint
    private var paintLine: Paint

    private var clickX = 0f
    private var clickY = 0f

    init {
        paintBackGround = Paint()
        paintBackGround.color = Color.BLACK
        paintBackGround.style = Paint.Style.FILL

        paintLine  = Paint()
        paintLine.color = Color.WHITE
        paintLine.strokeWidth = 5f
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintBackGround)

        canvas?.drawLine(0f, 0f, width.toFloat(), height.toFloat(), paintLine)

        if (clickX != 0f && clickY !=0f) {
            canvas?.drawCircle(clickX, clickY, 100f, paintLine)
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_DOWN) {
            clickX = event.x
            clickY = event.y

            invalidate() // redraws the view, so eventually the onDraw will be called again
        }


        return true
    }

}