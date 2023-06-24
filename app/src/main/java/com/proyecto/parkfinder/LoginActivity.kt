package com.proyecto.parkfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.proyecto.parkfinder.db.DAO
import com.proyecto.parkfinder.objetos.Usuario

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etClave = findViewById<EditText>(R.id.etClave)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        val tvNuevoUsuario = findViewById<TextView>(R.id.tvRegistrarNuevoUsuario)


        btnIngresar.setOnClickListener{
            val db : DAO = DAO(this)
           db.llenarTablaAtracciones()
         //   db.eliminarAtraccion()



            val usuarioObtenido : Usuario? = db.getUsuarioParaLogin(
                etUsuario.text.toString(),
                etClave.text.toString())

            if (usuarioObtenido != null) {
                val intent = Intent(this, InicioActivity::class.java)
                intent.putExtra("idUsuario", usuarioObtenido.id.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this, "Correo o Clave incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        tvNuevoUsuario.setOnClickListener{
            val intent = Intent(this, RegistroUsuarioActivity::class.java)
            startActivity(intent)
        }
    }


}