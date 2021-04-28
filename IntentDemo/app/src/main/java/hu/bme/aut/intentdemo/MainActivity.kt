package hu.bme.aut.intentdemo

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnIntent).setOnClickListener {
            //intentSearch()

            //intentCall()

            //intentShare()

            //intentWaze()

            intentStreetMaps()
        }
    }

    private fun intentSearch() {
        val intentSearch = Intent(Intent.ACTION_WEB_SEARCH)
        intentSearch.putExtra(SearchManager.QUERY, "AIT Budapest")
        startActivity(intentSearch)
    }

    private fun intentCall() {
        val intentCall = Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:+3612345675"))
        startActivity(intentCall)
    }

    private fun intentShare() {
        val intentShare = Intent(Intent.ACTION_SEND)
        intentShare.type = "text/plain"
        intentShare.putExtra(Intent.EXTRA_TEXT, "Share demo from App")
        intentShare.`package` = "com.facebook.katana"
        startActivity(intentShare)
    }

    private fun intentWaze() {
        //String wazeUri = "waze://?favorite=Home&navigate=yes";
        //val wazeUri = "waze://?ll=40.761043, -73.980545&navigate=yes"
        val wazeUri = "waze://?q=AIT%20Budapest&navigate=yes"

        val intentTest = Intent(Intent.ACTION_VIEW)
        intentTest.data = Uri.parse(wazeUri)
        startActivity(intentTest)
    }

    private fun intentStreetMaps() {
        val gmmIntentUri = Uri.parse(
                "google.streetview:cbll=29.9774614,31.1329645&cbp=0,30,0,0,-15")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}