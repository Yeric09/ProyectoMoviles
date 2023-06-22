package com.proyecto.parkfinder.objetos

class Comentario constructor(
    var id: Int = -1,
    var usuario: Usuario,
    var atraccion: Atraccion,
    var comentario: String = "",
    var cantidadEstrellas: Int = -1,
    var foto: ByteArray? = null //Es la imagen
)
