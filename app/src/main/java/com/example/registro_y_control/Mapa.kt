package com.example.registro_y_control

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.registro_y_control.db.OpenHelperDatabase
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Mapa : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map:GoogleMap
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mapa)
        sharedPreferences = getSharedPreferences("datos_globales", Context.MODE_PRIVATE)
        createFragment()


    }

    private fun createFragment(){
        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
    }
    //coordenadas
    private fun createMarker(){
        val cordinates = LatLng(5.524874, -73.365843)
        val marker = MarkerOptions().position(cordinates).title("Sede principal")
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(cordinates, 18f),
            4000,
            null
        )
    }
}
