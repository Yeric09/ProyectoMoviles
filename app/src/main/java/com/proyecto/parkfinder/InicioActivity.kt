package com.proyecto.parkfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        val btnMuseos = findViewById<ImageButton>(R.id.imageButtonMuseos)

        val btnTeatros = findViewById<ImageButton>(R.id.imageButtonTeatros)
        val btnParques = findViewById<ImageButton>(R.id.imageButtonParques)
        val btnMenuLat = findViewById<ImageButton>(R.id.imageButtonMenu)



        val redireccionar = findViewById<TextView>(R.id.BannerTxtView)
        redireccionar.setOnClickListener{
            val intent = Intent(this, AtraccionesPorCategoriaActivity::class.java)
            startActivity(intent)
        }
    }
}