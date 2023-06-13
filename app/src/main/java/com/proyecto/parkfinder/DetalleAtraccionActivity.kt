package com.proyecto.parkfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetalleAtraccionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_atraccion)

        val redireccionar = findViewById<TextView>(R.id.textView4)
        redireccionar.setOnClickListener{
            val intent = Intent(this, ComentariosAtraccionActivity::class.java)
            startActivity(intent)
        }
    }
}