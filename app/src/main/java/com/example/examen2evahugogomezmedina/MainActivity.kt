package com.example.examen2evahugogomezmedina

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val bar1 = LatLng(41.635126, -4.759613)
        val bar2 = LatLng(41.635529, -4.760146)
        val bar3 = LatLng(41.634121, -4.755390)

        mMap.addMarker(MarkerOptions().position(bar1).title("Bar 1").snippet("http://iesjulianmarias.centros.educa.jcyl.es/sitio/"))
        mMap.addMarker(MarkerOptions().position(bar2).title("Bar 2").snippet("http://iesjulianmarias.centros.educa.jcyl.es/sitio/"))
        mMap.addMarker(MarkerOptions().position(bar3).title("Bar 3").snippet("http://iesjulianmarias.centros.educa.jcyl.es/sitio/"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bar1, 15f))
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID;

        googleMap.setOnMarkerClickListener { marker ->
            val url = marker.snippet
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
            true
        }

        googleMap.setOnMapClickListener { latLng ->
            // Mostrar un Toast con la latitud y la longitud
            Toast.makeText(
                this,
                "Latitud: ${latLng.latitude}, Longitud: ${latLng.longitude}",
                Toast.LENGTH_LONG
            ).show()
            //Establezco un marcador con el evento
            googleMap.addMarker((MarkerOptions()
                .position(latLng)
                .title("Marcador en destino")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)))
                .draggable(true))
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_nombre, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nombre -> {
                mostrarNombre()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mostrarNombre() {
        Toast.makeText(this,"Opción: Acerca de", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_icono, menu)
    }




}