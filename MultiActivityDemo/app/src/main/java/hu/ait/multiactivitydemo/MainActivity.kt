package hu.ait.multiactivitydemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_DATA = "KEY_DATA"
        const val KEY_RES = "KEY_RES"
        const val REQ_DATA = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            val intentStart = Intent()
            intentStart.setClass(this, DetailsActivity::class.java)

            intentStart.putExtra(KEY_DATA, etData.text.toString())

            DataManager.listofData.add("demo3")

            startActivityForResult(intentStart, REQ_DATA)

            //finish()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_DATA) {
            if (resultCode == Activity.RESULT_OK) {
                val resp = data?.getStringExtra(KEY_RES)
                Toast.makeText(this, "Accept: $resp", Toast.LENGTH_LONG).show()
            }
            else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Declined", Toast.LENGTH_LONG).show()
            }
        }
    }


}