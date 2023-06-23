package com.proyecto.parkfinder

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.proyecto.parkfinder.db.DAO
import com.proyecto.parkfinder.objetos.Usuario

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        val btnMuseos = findViewById<ImageButton>(R.id.imageButtonMuseos)

        val btnTeatros = findViewById<ImageButton>(R.id.imageButtonTeatros)
        val btnParques = findViewById<ImageButton>(R.id.imageButtonParques)
        val nombreUsuario = findViewById<TextView>(R.id.nombreUsuario)
        val btnMenuLat = findViewById<ImageButton>(R.id.imageButtonMenu)

        nombreUsuario.text = "Hola, usuario"

        val extras = intent.extras
        var usuarioLogeado: String? = ""
        if (extras != null) {
            usuarioLogeado = extras.getString("idUsuario")

            val db : DAO = DAO(this)
            db.llenarTablaAtracciones()


            if (usuarioLogeado != null) {
                val usuario: Usuario? = db.getUsuarioPorId(usuarioLogeado)
                nombreUsuario.text = "Hola, ${usuario?.nombre}"
            }


        } else {
            nombreUsuario.text = "Hola, usuario"
        }

        btnMuseos.setOnClickListener() {
            val intent = Intent(this, AtraccionesPorCategoriaActivity::class.java)
            intent.putExtra("idUsuario", usuarioLogeado)
            intent.putExtra("idCategoria", "Museos")
            startActivity(intent)
        }

        btnTeatros.setOnClickListener() {
            val intent = Intent(this, AtraccionesPorCategoriaActivity::class.java)
            intent.putExtra("idUsuario", usuarioLogeado)
            intent.putExtra("idCategoria", "Teatros")
            startActivity(intent)
        }

        btnParques.setOnClickListener() {
            val intent = Intent(this, AtraccionesPorCategoriaActivity::class.java)
            intent.putExtra("idUsuario", usuarioLogeado)
            intent.putExtra("idCategoria", "Parques")
            startActivity(intent)
        }

//        val redireccionar = findViewById<TextView>(R.id.BannerTxtView)
//        redireccionar.setOnClickListener{
//            val intent = Intent(this, AtraccionesPorCategoriaActivity::class.java)
//            intent.putExtra("idUsuario", usuarioLogeado)
//            //  FALTA PARAMETRO DE LA CATEGORIA SELECCIONADA ASI:
//            intent.putExtra("idCategoria", categoriaSeleccionada)
//            startActivity(intent)
//        }
    }
}