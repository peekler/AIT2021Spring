package hu.ait.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnReset.setOnClickListener {
            ticView.resetGame()
            revealTicTacToe()
        }
    }

    public fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    public fun setStatusText(status: String) {
        tvStatus.text = status
    }

    fun revealTicTacToe() {
        val x = ticView.getRight()
        val y = ticView.getBottom()

        val startRadius = 0
        val endRadius = Math.hypot(ticView.getWidth().toDouble(),
            ticView.getHeight().toDouble()).toInt()

        val anim = ViewAnimationUtils.createCircularReveal(
            ticView,
            x,
            y,
            startRadius.toFloat(),
            endRadius.toFloat()
        )

        anim.duration = 3000

        ticView.setVisibility(View.VISIBLE)
        anim.start()
    }

    public fun isFlagModeOn() : Boolean {
        return buttonFlag.isChecked
    }

}