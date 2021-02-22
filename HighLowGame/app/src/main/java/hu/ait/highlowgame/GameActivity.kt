package hu.ait.highlowgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    var generatedNum = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        generateRandomNumber()

        btnGuess.setOnClickListener {

            try {
                if (etGuess.text.isNotEmpty()) {

                    val myGuess = etGuess.text.toString().toInt()

                    if (myGuess == generatedNum) {
                        tvStatus.text = "You have won!"
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
        generatedNum = Random(System.currentTimeMillis()).nextInt(100) //0..99
    }

}