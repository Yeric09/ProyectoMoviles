package com.proyecto.parkfinder

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageButton

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
        viewImagen = findViewById<ImageView>(R.id.registroImagenPerfil)

        btnImagen.setOnClickListener() {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            cambiarImagen.launch(intent)
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