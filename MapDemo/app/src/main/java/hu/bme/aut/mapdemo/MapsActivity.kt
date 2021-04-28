package hu.bme.aut.mapdemo

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.maps.android.clustering.ClusterManager
import kotlinx.android.synthetic.main.activity_maps.*
import java.util.*
import kotlin.concurrent.thread

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
    MainLocationManager.OnNewLocationAvailable {

    private lateinit var mMap: GoogleMap

    private lateinit var clusterManager: ClusterManager<MyMarkerClusterItem>

    private lateinit var mainLocationManager: MainLocationManager

    private var markerCurrentPosition : Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)

        mainLocationManager = MainLocationManager(this, this)

        requestNeededPermission()
    }

    fun requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
        } else {
            // we have the permission
            handleLocationStart()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            101 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "ACCESS_FINE_LOCATION perm granted", Toast.LENGTH_SHORT)
                        .show()

                    handleLocationStart()
                } else {
                    Toast.makeText(
                        this,
                        "ACCESS_FINE_LOCATION perm NOT granted", Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

    private fun handleLocationStart() {
        mainLocationManager.startLocationMonitoring()
    }

    override fun onStop() {
        mainLocationManager.stopLocationMonitoring()
        super.onStop()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val mapStyleOptions = MapStyleOptions.loadRawResourceStyle(this, R.raw.mymapstyle)
        mMap.setMapStyle(mapStyleOptions)

        mMap.isTrafficEnabled = true
        mMap.uiSettings.isZoomControlsEnabled = true

        btnMapMode.setOnClickListener {
            if (btnMapMode.isChecked) {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            } else {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            }
        }

        markerCurrentPosition = mMap.addMarker(MarkerOptions().position(LatLng(47.0, 19.0)).title("My Postion"))


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

        mMap.setOnMarkerClickListener { marker ->
            geocodeLocation(marker!!.position)
            false
        }

        //mMap.setOnMarkerDragListener()

        // UNCOMMENT IT FOR SHOWING A POLYGON
        /*val polyRect: PolygonOptions = PolygonOptions().add(
            LatLng(44.0, 19.0),
            LatLng(44.0, 26.0),
            LatLng(48.0, 26.0),
            LatLng(48.0, 19.0)
        )
        val polygon: Polygon = mMap.addPolygon(polyRect)
        polygon.fillColor = Color.argb(100, 0, 255, 0)*/

        // UNCOMMENT IT FOR SHOWING MARKER CLUSTER
        //initMarkerCluster()
    }

    fun initMarkerCluster() {
        clusterManager = ClusterManager(this, mMap)
        mMap.setOnCameraIdleListener(clusterManager)
        mMap.setOnMarkerClickListener(clusterManager)

        var lat = 40.5
        var lng = 10.5

        for (i in 0..20) {
            val offset = i / 60.0
            lat += offset
            lng += offset

            val newItem = MyMarkerClusterItem(lat, lng, "Title $i", "Snippet $i")
            clusterManager.addItem(newItem)
        }
    }

    var previousLocation: Location? = null
    var distance: Float = 0f

    override fun onNewLocation(location: Location) {
        if (previousLocation != null && location.accuracy < 20) {
            if (previousLocation!!.time < location.time) {
                distance += previousLocation!!.distanceTo(location)

            }
        }
        previousLocation = location


        val locationText = """
            ${location.latitude}
            ${location.longitude}
            Distance: $distance
            ${location.accuracy} m
            ${location.altitude}
            ${location.speed}
        """.trimIndent()

        tvLocationData.text = locationText

        markerCurrentPosition?.position = LatLng(location.latitude, location.longitude)
        mMap.animateCamera(CameraUpdateFactory.newLatLng(markerCurrentPosition?.position))
    }

    private fun geocodeLocation(location: LatLng) {
        thread {
            try {
                val gc = Geocoder(this, Locale.getDefault())
                var addrs: List<Address> =
                    gc.getFromLocation(location.latitude, location.longitude, 3)
                val addr =
                    "${addrs[0].getAddressLine(0)}, ${addrs[0].getAddressLine(1)}, ${addrs[0].getAddressLine(2)}"

                runOnUiThread {
                    Toast.makeText(this, addr, Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MapsActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

}