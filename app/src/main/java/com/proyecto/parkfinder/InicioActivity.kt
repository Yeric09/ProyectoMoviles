package com.proyecto.parkfinder

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        val btnMuseos = findViewById<ImageButton>(R.id.imageButtonMuseos)

        val btnTeatros = findViewById<ImageButton>(R.id.imageButtonTeatros)
        val btnParques = findViewById<ImageButton>(R.id.imageButtonParques)
        val btnMenuLat = findViewById<ImageButton>(R.id.imageButtonMenu)

        val extras = intent.extras
        var usuarioLogeado: String? = ""
        if (extras != null) {
            usuarioLogeado = extras.getString("idUsuario")
        }

        val redireccionar = findViewById<TextView>(R.id.BannerTxtView)
        redireccionar.setOnClickListener{
            val intent = Intent(this, AtraccionesPorCategoriaActivity::class.java)
            intent.putExtra("idUsuario", usuarioLogeado)
            //  FALTA PARAMETRO DE LA CATEGORIA SELECCIONADA ASI:
            //intent.putExtra("idCategoria", categoriaSeleccionada)
            startActivity(intent)
        }
    }
}