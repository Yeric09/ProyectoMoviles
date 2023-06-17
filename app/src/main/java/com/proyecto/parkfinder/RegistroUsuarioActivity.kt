package com.proyecto.parkfinder

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageButton
import com.proyecto.parkfinder.db.DAO
import com.proyecto.parkfinder.objetos.Usuario

class RegistroUsuarioActivity : AppCompatActivity() {

    private lateinit var viewImagen : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        val textoNombre = findViewById<EditText>(R.id.registroTextoNombre)
        val textoCorreo = findViewById<EditText>(R.id.registroTextoCorreo)
        val textoClave = findViewById<EditText>(R.id.registroTextoContrasena)
        val btnRegistrar = findViewById<Button>(R.id.registroBotonRegistrar)
        val btnImagen = findViewById<ImageButton>(R.id.registroBotonImagen)
        val viewImagen = findViewById<ImageView>(R.id.registroImagenPerfil)

        //Agrega una imagen del celular como foto de perfil
        btnImagen.setOnClickListener() {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            cambiarImagen.launch(intent)
        }

        //Agrega usuario nuevo a la base de datos.
        btnRegistrar.setOnClickListener() {
            val db = DAO(this)
            val cv = Usuario(-1,
                textoNombre.text.toString(),
                textoCorreo.text.toString(),
                textoClave.text.toString(),
                viewImagen.toString().encodeToByteArray())

            val llamada : Boolean = db.agregarUsuario(cv)

            if (llamada) {
                Toast.makeText(this, "Usuario agregado correctamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error agregando usuario", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private val cambiarImagen =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val imgUri = data?.data
                viewImagen.setImageURI(imgUri)
            } else {
                print(it.resultCode)
            }
        }

}