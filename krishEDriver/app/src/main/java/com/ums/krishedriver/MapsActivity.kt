package com.ums.krishedriver

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
var getting:maplat?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        supportActionBar?.title="Farmers & Industries Locations"
        getting=intent.getParcelableExtra<maplat>("GET")
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
     mMap.isMyLocationEnabled=true
        // Add a marker in Sydney and move the camera
        val farmer= LatLng(getting!!.lat1, getting!!.long1)
        mMap.addMarker(MarkerOptions().position(farmer).title("Farmer Location").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
        ))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(farmer,10f))
        val industry= LatLng(getting!!.lat2, getting!!.long2)
        mMap.addMarker(MarkerOptions().position(industry).title("Industry Location").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
        ))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(industry,10f))
    }
}