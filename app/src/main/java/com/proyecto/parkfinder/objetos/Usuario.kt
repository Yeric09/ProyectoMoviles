package com.proyecto.parkfinder.objetos

class Usuario constructor(
    var id: Int = -1,
    var nombre: String = "",
    var correo: String = "",
    var contrasena: String = "",
    var icono: ByteArray? = null
)