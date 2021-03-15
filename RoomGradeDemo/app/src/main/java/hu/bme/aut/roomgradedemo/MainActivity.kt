package hu.bme.aut.roomgradedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.roomgradedemo.data.AppDatabase
import hu.bme.aut.roomgradedemo.data.Grade
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSubmit.setOnClickListener {
            val grade = Grade(null, etStudentId.text.toString(), etGrade.text.toString())

            thread {
                AppDatabase.getInstance(this).gradeDao().insertGrades(grade)
            }
        }

        btnSearch.setOnClickListener {
            thread {
                val grades = AppDatabase.getInstance(this).gradeDao().getAllGrades()

                runOnUiThread {
                    tvResult.text = ""

                    grades.forEach {
                        tvResult.append("${it.studentId} ${it.grade}\n")
                    }
                }
            }
        }
    }
}