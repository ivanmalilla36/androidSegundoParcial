package com.example.egghunt3r.favoritoapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DetailFavorito : AppCompatActivity(), OnMapReadyCallback {
    override fun onMapReady(googleMap: GoogleMap) {
        val sydney = LatLng(28.6097544,-106.12645690000001)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,14f))
    }

    private lateinit var titulo: TextView
    private lateinit var descripcion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_favorito)

        titulo = findViewById(R.id.title)
        descripcion = findViewById(R.id.desc)

        titulo.text = intent.extras.getString("title")
        descripcion.text = intent.extras.getString("description")

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }
}
