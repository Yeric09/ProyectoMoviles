package com.proyecto.parkfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import com.proyecto.parkfinder.db.DAO
import com.proyecto.parkfinder.objetos.Atraccion
import com.proyecto.parkfinder.objetos.Usuario

class AtraccionesPorCategoriaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atracciones_por_categoria)

        val categoria = findViewById<TextView>(R.id.categoria)
        val nombreUsuario = findViewById<TextView>(R.id.nombreUsuarioEnActividad)
        val listaAtracciones = findViewById<ListView>(R.id.listaAtracciones)

        val extras = intent.extras
        var usuarioLogeado: String? = ""
        var categoriaActual: String? = ""
        if (extras != null) {
            usuarioLogeado = extras.getString("idUsuario")

            val db : DAO = DAO(this)

            if (usuarioLogeado != null) {
                val usuario: Usuario? = db.getUsuarioPorId(usuarioLogeado)
                nombreUsuario.text = "Hola, ${usuario?.nombre}"
            }
            categoriaActual = extras.getString("idCategoria")


            if (categoriaActual != null) {
                categoria.text = categoriaActual
                val atracciones: List<Atraccion> = db.getAtracciones_X_Categoria(categoriaActual)

              val atraccionesArrayAdapter : ArrayAdapter<Atraccion> =
                  ArrayAdapter(this, android.R.layout.simple_list_item_1, atracciones)
                   listaAtracciones.adapter = atraccionesArrayAdapter

                atracciones.get(1).nombre.toString()
            }





        }


        intent.putExtra("idUsuario", usuarioLogeado)

        //intent.putExtra("idAtraccion", atraccionSeleccionada)
//        val redireccionar = findViewById<TextView>(R.id.textView3)
//        redireccionar.setOnClickListener{
//            val intent = Intent(this, DetalleAtraccionActivity::class.java)
//            startActivity(intent)
//        }
    }
}