package hu.ait.highlowgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    companion object {
        const val KEY_GEN = "KEY_GEN"
    }


    var generatedNum = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_GEN)) {
            generatedNum = savedInstanceState.getInt(KEY_GEN)
        } else {
            generateRandomNumber()
        }

        btnGuess.setOnClickListener {

            try {
                if (etGuess.text.isNotEmpty()) {

                    val myGuess = etGuess.text.toString().toInt()

                    if (myGuess == generatedNum) {
                        tvStatus.text = "You have won!"

                        startActivity(Intent(this, ResultActivity::class.java))

                    } else if (myGuess < generatedNum) {
                        tvStatus.text = "The number is higher"
                    } else {
                        tvStatus.text = "The number is lower"
                    }
                } else {
                    etGuess.error = "This field can not be empty"
                }
            } catch (e: Exception){
                etGuess.error = e.message
            }

        }
    }

    fun generateRandomNumber() {
        generatedNum = Random(System.currentTimeMillis()).nextInt(5) //0..99
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_GEN, generatedNum)
    }



}