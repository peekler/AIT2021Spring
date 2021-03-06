package hu.ait.threadandtimerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {

    private inner class MyThread : Thread() {

        override fun run() {
            while (enabled) {

                runOnUiThread {
                    tvData.append("#")
                }

                sleep(2000)
            }
        }
    }

    private inner class MyTimerTask : TimerTask() {
        override fun run() {
            runOnUiThread {
                tvData.append("%")
            }
        }
    }


    private var enabled = false
    private val timerMain = Timer()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            //enabled = true
            //MyThread().start()
            //tvData.append("$")

            timerMain.schedule(MyTimerTask(), 2000, 1000)
        }

        btnStop.setOnClickListener {
            enabled = false

            timerMain.cancel()
        }
    }

    override fun onStop() {
        enabled = false

        try {
            timerMain.cancel()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        super.onStop()
    }

}