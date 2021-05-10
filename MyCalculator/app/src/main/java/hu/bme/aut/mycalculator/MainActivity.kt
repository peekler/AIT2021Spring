package hu.bme.aut.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
            if (isFormValid()) {
                try {
                    val numA = etNumA.text.toString().toInt()
                    val numB = etNumB.text.toString().toInt()

                    tvResult.text = "Result: ${numA + numB}"
                } catch (e: Exception) {
                    tvResult.text = e.message
                }
            }
        }

        btnMinus.setOnClickListener {
            if (isFormValid()) {
                try {
                    val numA = etNumA.text.toString().toInt()
                    val numB = etNumB.text.toString().toInt()

                    tvResult.text = "Result: ${numA - numB}"
                } catch (e: Exception) {
                    tvResult.text = e.message
                }
            }
        }
    }

    fun isFormValid() : Boolean {
        if (etNumA.text.isEmpty()) {
            etNumA.error = "This field can not be empty"
            return false
        }
        if (etNumB.text.isEmpty()) {
            etNumB.error = "This field can not be empty"
            return false
        }

        return true
    }

}