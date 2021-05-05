package hu.bme.aut.sensordemo

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        btnSensorList.setOnClickListener {
            val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
            tvData.text = ""
            sensors.forEach {
                tvData.append("${it.name}\n")
            }
        }

        btnStart.setOnClickListener {
            val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        sensorManager.unregisterListener(this)
    }




    override fun onSensorChanged(event: SensorEvent?) {
        var sensorX = event!!.values.get(0)
        var sensorY = event!!.values.get(1)
        var sensorZ = event!!.values.get(2)

        tvData.text="x: $sensorX, y: $sensorY, z: $sensorZ"
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


}