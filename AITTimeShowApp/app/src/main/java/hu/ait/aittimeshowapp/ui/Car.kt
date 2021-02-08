package hu.ait.aittimeshowapp.ui

import android.util.Log
import kotlin.math.max

class Car(var brand: String = "Tesla") {

    init {
        Log.d("TAG_CAR", "$brand")
    }

    var maxSpeed = 0

    constructor(brand: String, maxSpeed: Int) : this(brand){
        this.maxSpeed = maxSpeed
    }

    fun getData() : String {
        return "$brand"
    }

}