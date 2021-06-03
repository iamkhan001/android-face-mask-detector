package sg.mirobotic.robot.config

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import sg.mirobotic.face.csjsdk.R

class MyLocation (private val activity: Activity, private val onLocationUpdateListener: OnLocationUpdateListener) {

    companion object {
        private const val TAG = "MyLocation"
    }

    private var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)

    @SuppressLint("MissingPermission")
    fun getLastLocation() {
        fusedLocationClient.lastLocation
            .addOnCompleteListener { taskLocation ->
                if (taskLocation.isSuccessful && taskLocation.result != null) {

                    val location = taskLocation.result

                    if (location != null) {
                        onLocationUpdateListener.onLocationUpdate(location.latitude, location.longitude)
                    }


                } else {
                    Log.w(TAG, "getLastLocation:exception", taskLocation.exception)

                    onLocationUpdateListener.onError(activity.getString(R.string.no_location))
                }
            }
    }

    interface OnLocationUpdateListener {

        fun onLocationUpdate(lat: Double, lon: Double)
        fun onError(msg: String)

    }


}