package hu.ait.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

data class Ship(var name: String, var age: Int)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvData.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

            }
        })

        tvData.logToFile()


        var myCar = Car("Nissan", "Leaf")
        myCar.name

        demo()

        //var (shipname, age) = Ship("Enterprise", 30)

        var ship = Ship("Enterprise", 30)
        var shipname = ship.name
        var age= ship.age
    }

    fun TextView.logToFile(){

    }
}