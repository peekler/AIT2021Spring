package hu.ait.aittimeshowapp.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.annotation.RequiresApi
import hu.ait.aittimeshowapp.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnShow.setOnClickListener {
            tvData.text = Date(System.currentTimeMillis()).toString()

            revealCard()
        }
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun revealCard() {
        val x = cardView.getRight()
        val y = cardView.getBottom()

        val startRadius = 0
        val endRadius = Math.hypot(cardView.getWidth().toDouble(),
                cardView.getHeight().toDouble()).toInt()

        val anim = ViewAnimationUtils.createCircularReveal(
                cardView,
                x,
                y,
                startRadius.toFloat(),
                endRadius.toFloat()
        )

        cardView.setVisibility(View.VISIBLE)
        anim.start()
    }

}