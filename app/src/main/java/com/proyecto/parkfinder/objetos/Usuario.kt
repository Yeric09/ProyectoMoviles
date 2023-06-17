package com.proyecto.parkfinder.objetos

//Objeto usuario utlizado para agregar y retornar de la base de datos
class Usuario constructor(
    var id: Int = -1,
    var nombre: String = "",
    var correo: String = "",
    var contrasena: String = "",
    var icono: ByteArray? = null //Es la imagen
)