package hu.bme.aut.musicplayerdemo

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), MediaPlayer.OnPreparedListener {

    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.demo)
            mediaPlayer.setOnPreparedListener(this)
        }

        btnStop.setOnClickListener {
            mediaPlayer.seekTo(61000)

            /*try {
                mediaPlayer?.stop()
            }catch (e: Exception) {
                e.printStackTrace()
            }*/
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mediaPlayer.start()
    }

    override fun onStop() {
        mediaPlayer.stop()
        super.onStop()
    }

}