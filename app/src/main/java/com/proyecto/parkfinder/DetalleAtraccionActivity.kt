package com.proyecto.parkfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetalleAtraccionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_atraccion)

        val extras = intent.extras
        var usuarioLogeado: String? = ""
        //var idAtraccionActual: String? = ""
        if (extras != null) {
            usuarioLogeado = extras.getString("idUsuario")
            //idAtraccionActual = extras.getString("idAtraccion)
        }

        intent.putExtra("idUsuario", usuarioLogeado)
        //  FALTA PARAMETRO DE LA ATRACCION SELECCIONADA ASI:
        //intent.putExtra("idAtraccion", atraccionSeleccionada)
        val redireccionar = findViewById<TextView>(R.id.txtViewTitulo)
        redireccionar.setOnClickListener{
            val intent = Intent(this, ComentariosAtraccionActivity::class.java)
            startActivity(intent)
        }
    }
}