package hu.ait.tictactoe.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import hu.ait.tictactoe.MainActivity
import hu.ait.tictactoe.R
import hu.ait.tictactoe.model.TicTacToeModel
import kotlinx.android.synthetic.main.activity_main.*


class TicTacToeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var paintBackGround: Paint
    private var paintLine: Paint
    private var paintText: Paint

    var bitmapBg = BitmapFactory.decodeResource(
        context?.resources, R.drawable.grass
    )

    private var tmpPlayer: PointF? = null


    init {
        paintBackGround = Paint()
        paintBackGround.color = Color.BLACK
        paintBackGround.style = Paint.Style.FILL

        paintLine = Paint()
        paintLine.color = Color.WHITE
        paintLine.strokeWidth = 5f
        paintLine.style = Paint.Style.STROKE

        paintText = Paint()
        paintText.color = Color.GREEN
        paintText.textSize = 60f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        paintText.textSize = height / 3f

        bitmapBg = Bitmap.createScaledBitmap(bitmapBg, width / 3, height / 3, false)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintBackGround)

        canvas?.drawBitmap(bitmapBg, 0f, 0f, null)

        drawGameArea(canvas)

        drawPlayers(canvas)

        drawTmpPlayer(canvas)

        canvas?.drawText("5", 0f, height / 3f, paintText)

    }

    private fun drawGameArea(canvas: Canvas) {
        // border
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintLine)

        // two horizontal lines
        canvas.drawLine(
            0f, (height / 3).toFloat(), width.toFloat(), (height / 3).toFloat(),
            paintLine
        )
        canvas.drawLine(
            0f, (2 * height / 3).toFloat(), width.toFloat(),
            (2 * height / 3).toFloat(), paintLine
        )

        // two vertical lines
        canvas.drawLine(
            (width / 3).toFloat(), 0f, (width / 3).toFloat(), height.toFloat(),
            paintLine
        )
        canvas.drawLine(
            (2 * width / 3).toFloat(), 0f, (2 * width / 3).toFloat(), height.toFloat(),
            paintLine
        )
    }

    private fun drawPlayers(canvas: Canvas) {
        for (i in 0..2) {
            for (j in 0..2) {
                if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CIRCLE) {
                    val centerX = (i * width / 3 + width / 6).toFloat()
                    val centerY = (j * height / 3 + height / 6).toFloat()
                    val radius = height / 6 - 2

                    canvas.drawCircle(centerX, centerY, radius.toFloat(), paintLine)
                } else if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CROSS) {
                    canvas.drawLine(
                        (i * width / 3).toFloat(), (j * height / 3).toFloat(),
                        ((i + 1) * width / 3).toFloat(),
                        ((j + 1) * height / 3).toFloat(), paintLine
                    )

                    canvas.drawLine(
                        ((i + 1) * width / 3).toFloat(), (j * height / 3).toFloat(),
                        (i * width / 3).toFloat(), ((j + 1) * height / 3).toFloat(), paintLine
                    )
                }
            }
        }
    }

    private fun drawTmpPlayer(canvas: Canvas) {
        if (tmpPlayer != null) {
            if (TicTacToeModel.nextPlayer
                === TicTacToeModel.CIRCLE
            ) {
                canvas.drawCircle(
                    tmpPlayer!!.x, tmpPlayer!!.y, (height / 6 - 2).toFloat(),
                    paintLine
                )
            } else {
                canvas.drawLine(
                    tmpPlayer!!.x - width / 6,
                    tmpPlayer!!.y - height / 6,
                    tmpPlayer!!.x + width / 6,
                    tmpPlayer!!.y + height / 6, paintLine
                )
                canvas.drawLine(
                    tmpPlayer!!.x - width / 6,
                    tmpPlayer!!.y + height / 6,
                    tmpPlayer!!.x + width / 6,
                    tmpPlayer!!.y - height / 6, paintLine
                )
            }
        }
    }




    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_MOVE) {
            tmpPlayer = PointF(event.x, event.y)
            invalidate()
        }
        else if (event?.action == MotionEvent.ACTION_UP) {


            tmpPlayer = null

            val tX = event.x.toInt() / (width/3)
            val tY = event.y.toInt() / (height/3)

            if (tX<3 && tY<3 && TicTacToeModel.getFieldContent(tX, tY) == TicTacToeModel.EMPTY) {

                TicTacToeModel.setFieldContent(tX, tY, TicTacToeModel.nextPlayer)
                TicTacToeModel.changeNextPlayer()
                invalidate() // redraws the view, so eventually the onDraw will be called again

                (context as MainActivity).showMessage(context.getString(R.string.text_next_turn))

                val next = if (TicTacToeModel.nextPlayer == TicTacToeModel.CIRCLE) "O" else "X"
                var textStatus = context.getString(R.string.text_next_player, next)
                (context as MainActivity).setStatusText(textStatus)
            }

        }



        return true
    }

    public fun resetGame(){
        TicTacToeModel.resetModel()
        invalidate()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = View.MeasureSpec.getSize(widthMeasureSpec)
        val h = View.MeasureSpec.getSize(heightMeasureSpec)
        val d = if (w == 0) h else if (h == 0) w else if (w < h) w else h
        setMeasuredDimension(d, d)
    }


}