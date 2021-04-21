package hu.bme.aut.mapdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_maps.*
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.isTrafficEnabled=true
        mMap.uiSettings.isZoomControlsEnabled = true

        btnMapMode.setOnClickListener {
            if (btnMapMode.isChecked) {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            } else {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
        }

        val marker = LatLng(47.0, 19.0)
        mMap.addMarker(MarkerOptions().position(marker).title("Marker in Hungary"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))


        mMap.setOnMapClickListener {
            var newMarker = mMap.addMarker(
                    MarkerOptions()
                            .position(it)
                            .title("${it.latitude}, ${it.longitude}")
                            .snippet("This is a new marker")
            )
            newMarker.isDraggable = true

            //mMap.animateCamera(CameraUpdateFactory.newLatLng(it))

            val random = Random(System.currentTimeMillis())
            val cameraPostion = CameraPosition.Builder()
                    .target(it)
                    .zoom(5f + random.nextInt(15))
                    .tilt(30f + random.nextInt(15))
                    .bearing(-45f + random.nextInt(90))
                    .build()
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPostion))
        }

        //mMap.setOnMarkerDragListener()



        val polyRect: PolygonOptions = PolygonOptions().add(
                LatLng(44.0, 19.0),
                LatLng(44.0, 26.0),
                LatLng(48.0, 26.0),
                LatLng(48.0, 19.0)
        )
        val polygon: Polygon = mMap.addPolygon(polyRect)
        polygon.fillColor = Color.argb(100, 0, 255, 0)
    }
}