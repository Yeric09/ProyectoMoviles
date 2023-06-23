package com.proyecto.parkfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ComentariosAtraccionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comentarios_atraccion)

        val extras = intent.extras
        var usuarioLogeado: String? = ""
        //var atraccionActual: String? = ""
        if (extras != null) {
            usuarioLogeado = extras.getString("idUsuario")
            //atraccionActual = extras.getString("idAtraccion")
        }
    }
}