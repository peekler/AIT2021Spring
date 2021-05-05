package hu.bme.aut.broadcastreceiverdemo

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 1001
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.RECEIVE_SMS,
                Manifest.permission.PROCESS_OUTGOING_CALLS,
                Manifest.permission.RECEIVE_BOOT_COMPLETED)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (allPermissionsGranted()) {
            //
        } else {
            ActivityCompat.requestPermissions(
                    this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }
    }


    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
                baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
            requestCode: Int, permissions: Array<String>, grantResults:
            IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                //setViews()
                Toast.makeText(this, "Permissions granted.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                        this,
                        "Permissions not granted by the user.",
                        Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }


}