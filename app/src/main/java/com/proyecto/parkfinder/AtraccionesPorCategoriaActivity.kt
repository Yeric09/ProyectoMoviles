package com.proyecto.parkfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AtraccionesPorCategoriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atracciones_por_categoria)


        val extras = intent.extras
        var usuarioLogeado: String? = ""
        //var categoriaActual: String? = ""
        if (extras != null) {
            usuarioLogeado = extras.getString("idUsuario")
            //categoriaActual = extras.getString("idCategoria")
        }


        intent.putExtra("idUsuario", usuarioLogeado)
        //  FALTA PARAMETRO DE LA ATRACCION SELECCIONADA ASI:
        //intent.putExtra("idAtraccion", atraccionSeleccionada)
        val redireccionar = findViewById<TextView>(R.id.textView3)
        redireccionar.setOnClickListener{
            val intent = Intent(this, DetalleAtraccionActivity::class.java)
            startActivity(intent)
        }
    }
}