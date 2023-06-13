package com.proyecto.parkfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val redireccionar = findViewById<TextView>(R.id.textView2)
        redireccionar.setOnClickListener{
            val intent = Intent(this, AtraccionesPorCategoriaActivity::class.java)
            startActivity(intent)
        }
    }
}