package hu.bme.aut.servicedemo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceIntent = Intent(this, ServiceTime::class.java)

        btnStart.setOnClickListener {
            startService(serviceIntent)
        }

        btnStop.setOnClickListener {
            stopService(serviceIntent)
        }

        requestNeededPermission()
    }


    private fun requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.FOREGROUND_SERVICE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.FOREGROUND_SERVICE),
                101)
        } else {

        }
    }



    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            101 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this@MainActivity, "Permissions granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity,
                        "Permissions are NOT granted", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

}