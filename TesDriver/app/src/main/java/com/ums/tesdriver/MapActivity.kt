package com.ums.tesdriver

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity() ,OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    var getting: Delivery? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        supportActionBar?.title = "Fleshers & Buyers Locations"
        getting = intent.getParcelableExtra<Delivery>("LAT")
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.isMyLocationEnabled = true
        val buyer = LatLng(getting!!.buyerlat.toDouble(), getting!!.buyerlong.toDouble())
        mMap.addMarker(
            MarkerOptions().position(buyer).title(getting!!.buyername+","+getting!!.buyermobileno).icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
            )
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(buyer, 10f))
        val flesher = LatLng(getting!!.flesherlat.toDouble(),getting!!.flesherlong.toDouble())
        mMap.addMarker(
            MarkerOptions().position(flesher).title(getting!!.fleshername+","+getting!!.fshopname+","+getting!!.flesherphone).icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
            )
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(flesher, 10f))
    }
}