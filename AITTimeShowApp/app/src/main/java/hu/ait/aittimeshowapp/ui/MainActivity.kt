package hu.ait.aittimeshowapp.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import hu.ait.aittimeshowapp.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnShow.setOnClickListener {
            var dateText = etName.text.toString()+" "+getString(R.string.text_time, Date(System.currentTimeMillis()).toString())

            Log.d("TAG_DEMO", dateText)


            tvData.text = dateText
            Toast.makeText(this, dateText,
                    Toast.LENGTH_LONG).show()


            Snackbar.make(layoutMain, dateText, Snackbar.LENGTH_LONG).show()

            val car = Car()


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