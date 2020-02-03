package rbl.Assignment.weatherapp.weather.view

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.android.synthetic.main.activity_main.*
import rbl.Assignment.weatherapp.R
import rbl.Assignment.weatherapp.common.Utils

class WeatherActivity : BaseActivity() {

    var FASTEST_INTERVAL: Long = 200 // 8 SECOND
    var UPDATE_INTERVAL: Long = 200 // 2 SECOND
    var FINE_LOCATION_REQUEST: Int = 888
    lateinit var locationRequest: LocationRequest



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (checkPermissions()) {
            initLocationUpdate()
            initOnlineFlow()
        }else{
            initOfflineFlow()
        }

    }

    override fun initOnlineFlow() {
        initWeatherSuccessFragment()
    }

    override fun initOfflineFlow() {
        initWeatherErrorFragment()
    }

    private fun initWeatherErrorFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.layout_container,
                WeatherErrorFragment()
            )
            .commit()
    }

    private fun initWeatherSuccessFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.layout_container,
                WeatherSuccessFragment()
            )
            .commit()
    }

    private fun checkPermissions(): Boolean {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true
        } else {
            requestPermissions()
            return false
        }
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            FINE_LOCATION_REQUEST)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == FINE_LOCATION_REQUEST) {
            // Received permission result for Location permission.

            // Check if the only required permission has been granted
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission has been granted, preview can be displayed
                initLocationUpdate()
            } else {
                Snackbar.make(layout_container, R.string.rational_location_permission,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.ok), object : View.OnClickListener {
                        override fun onClick(p0: View?) {
                            requestPermissions()
                        }
                    })
                    .show()

            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    fun onLocationChanged(location: Location) {
        // New location has now been determined

        // val geoWeatherRequest = APICons("${location?.latitude}", "${location?.longitude}")
        // locationDataStore.save(location.latitude, location.longitude)

        Log.i(
            "Location",
            location.latitude.toString() + " " + location.longitude.toString())
        Utils.setLattitude(this,location.latitude.toString() )
        Utils.setLognitude(this,location.longitude.toString() )
    }






    fun initLocationUpdate() {
        //init location request to start retrieving location update
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = UPDATE_INTERVAL
        locationRequest.fastestInterval = FASTEST_INTERVAL

        //Create LocationSettingRequest object using locationRequest
        val locationSettingBuilder: LocationSettingsRequest.Builder = LocationSettingsRequest.Builder()
        locationSettingBuilder.addLocationRequest(locationRequest)
        val locationSetting: LocationSettingsRequest = locationSettingBuilder.build()

        //Need to check whether location settings are satisfied
        val settingsClient: SettingsClient = LocationServices.getSettingsClient(this)
        settingsClient.checkLocationSettings(locationSetting)
        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        val fusedLocationProviderClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                //super.onLocationResult(p0)
                if (locationResult != null) {
                    onLocationChanged(locationResult.lastLocation)
                }
            }

            override fun onLocationAvailability(p0: LocationAvailability?) {
                super.onLocationAvailability(p0)
            }
        },
            Looper.myLooper())

    }


    fun getLastKnowLocation() {
        // Get last known recent location using new Google Play Services SDK (v11+)
        val locationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationClient.lastLocation
            .addOnSuccessListener(OnSuccessListener<Location> { location ->
                // GPS location can be null if GPS is switched off
                if (location != null) {
                    onLocationChanged(location)
                }
            })
            .addOnFailureListener(OnFailureListener { e ->
                e.printStackTrace()
            })
    }
}
