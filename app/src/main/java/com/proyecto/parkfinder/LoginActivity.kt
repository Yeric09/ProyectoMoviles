package com.proyecto.parkfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.proyecto.parkfinder.db.DAO

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etClave = findViewById<EditText>(R.id.etClave)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        val tvNuevoUsuario = findViewById<TextView>(R.id.tvRegistrarNuevoUsuario)


        btnIngresar.setOnClickListener{
            if(etUsuario.text.toString().equals("Luis")  && etClave.text.toString().equals("123")) {
                val intent = Intent(this, InicioActivity::class.java)
                startActivity(intent)
            }

            val db : DAO = DAO(this)
        }

        tvNuevoUsuario.setOnClickListener{
            val intent = Intent(this, RegistroUsuarioActivity::class.java)
            startActivity(intent)
        }
    }


}