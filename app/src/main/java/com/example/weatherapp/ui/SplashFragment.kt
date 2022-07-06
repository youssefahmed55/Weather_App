package com.example.weatherapp.ui
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.delay


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onResume() {
        super.onResume()

            if (firstTime) {
                Handler(Looper.getMainLooper()).postDelayed(Runnable { getCurrentLocation() }, 3000)
                firstTime = false
            } else {
                getCurrentLocation()
            }
            Log.d(TAG, "onResume: ")

    }
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var splashView : View
    private var firstTime : Boolean = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        splashView  = inflater.inflate(R.layout.fragment_splash, container, false) //Initialize splashView

        return splashView
    }
    private fun getCurrentLocation(){
        if(checkPermission()){         //Check Get Location Permission
           if(isLocationEnabled()){    //Check If Location Enabled
               fusedLocationListener() //Listener For Location

           }else{
              Toast.makeText(this.activity,"Turn On Location Please",Toast.LENGTH_SHORT).show()
              val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
              startActivity(intent)    //Intent To Enable Location
           }

        }else{
             requestPermission()       //Request Permission
        }
    }

    private fun fusedLocationListener() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.activity!!) //Initialize fusedLocationClient
        fusedLocationClient.lastLocation.addOnCompleteListener(this.activity!!){  task ->
            if(task.result != null){
                navigateWithSendLatAndLon(task.result.latitude,task.result.longitude)
            }else{
                var locationRequest: com.google.android.gms.location.LocationRequest = com.google.android.gms.location.LocationRequest()
                locationRequest.interval = 10000
                locationRequest.fastestInterval = 1000
                locationRequest.numUpdates = 1
                locationRequest.priority = com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY

                var locationCallback : LocationCallback = object : LocationCallback (){
                    override fun onLocationResult(p0: LocationResult) {
                        super.onLocationResult(p0)
                        val location =
                            p0.lastLocation
                        if (location != null) {
                            // get latest location
                            navigateWithSendLatAndLon(location.latitude,location.longitude)
                            // use your location object
                            // get latitude , longitude and other info from this
                        }else{
                            Log.d(TAG, "onLocationResult: empty ")
                        }
                    }

                }
                fusedLocationClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    null /* Looper */
                )
            }

        }
    }
    fun navigateWithSendLatAndLon(lat : Double , lon : Double){
        var bundle = Bundle()        //Initialize Bundle
        bundle.putDouble("lat" , lat)
        bundle.putDouble("lon",lon)

        Log.d(TAG, "navigateWithSendLatAndLon: lat: $lat  , lon: $lon")

        lifecycleScope.launchWhenStarted {
            delay(1L)
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment,bundle) //Navigate To Home Fragment and Send Bundle Data
        }



    }

    private fun isLocationEnabled():Boolean{
        val locationManager:LocationManager  = this.activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun requestPermission() {
        /*ActivityCompat.requestPermissions(
         this.activity!! , arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
         android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_ACCESS_LOCATION)*/
         requestPermissions(arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_ACCESS_LOCATION)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_ACCESS_LOCATION){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d(TAG, "onRequestPermissionsResult: Granted")
                //getCurrentLocation()

            }else{
                Log.d(TAG, "onRequestPermissionsResult: Denied")
                Toast.makeText(this.activity,"Permission Denied",Toast.LENGTH_LONG).show()
            }
        }

    }




    companion object {
        private const val TAG = "SplashFragment"
        private const val PERMISSION_REQUEST_ACCESS_LOCATION=100

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SplashFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SplashFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun checkPermission() :Boolean{
      if(ActivityCompat.checkSelfPermission(this.context!!,android.Manifest.permission.ACCESS_COARSE_LOCATION)
         == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.context!!,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ){
          return true
      }

        return false
    }
}