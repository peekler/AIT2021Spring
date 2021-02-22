package hu.ait.multiactivitydemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        if (intent.hasExtra(MainActivity.KEY_DATA)) {
            tvData.text = intent.getStringExtra(MainActivity.KEY_DATA)

            //tvData.text = DataManager.listofData.get(2)
        }

        btnAccept.setOnClickListener {
            val intentResult = Intent()
            intentResult.putExtra(MainActivity.KEY_RES, "accept")

            setResult(Activity.RESULT_OK, intentResult)
            finish()
        }
        btnDecline.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        //Toast.makeText(this, "You can never quit", Toast.LENGTH_LONG).show()
    }


}